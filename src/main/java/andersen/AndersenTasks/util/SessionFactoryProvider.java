package andersen.AndersenTasks.util;

import andersen.AndersenTasks.models.BusTicket;
import andersen.AndersenTasks.models.User;
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
                configuration.addAnnotatedClass(BusTicket.class);
                configuration.addAnnotatedClass(User.class);
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