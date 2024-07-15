package andersen.AndersenTasks.repository;

import andersen.AndersenTasks.models.User;
import andersen.AndersenTasks.util.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {
    public void save(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void delete (int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.remove(user);
        transaction.commit();
        session.close();
    }

    public User fetchById(int id) {
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .get(User.class, id);
    }
}
