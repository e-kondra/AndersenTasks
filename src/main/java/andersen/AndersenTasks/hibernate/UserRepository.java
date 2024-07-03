package andersen.AndersenTasks.hibernate;

import andersen.AndersenTasks.busticket.BusTicketType;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepository {
    public void save(UserNew user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void delete (int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        UserNew user = session.get(UserNew.class, id);
        session.remove(user);
        transaction.commit();
        session.close();
    }

    public UserNew fetchById(int id) {
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .get(UserNew.class, id);
    }
}
