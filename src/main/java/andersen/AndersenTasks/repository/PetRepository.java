package andersen.AndersenTasks.repository;

import andersen.AndersenTasks.models.Pet;
import andersen.AndersenTasks.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class PetRepository {
    public void save(Pet pet) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(pet);
        transaction.commit();
        session.close();
    }
}
