package andersen.AndersenTasks.hibernate;

import andersen.AndersenTasks.ticket.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {
    private static  SessionFactory sessionFactory;
    private SessionFactoryProvider(){}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(BusTicketNew.class);
                configuration.addAnnotatedClass(UserNew.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            }catch (Exception e) {
                System.out.println("An error occurred during Hibernate init " + e);
            }
        }
        return sessionFactory;
    }
}
