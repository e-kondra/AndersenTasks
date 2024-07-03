package andersen.AndersenTasks.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BusTicketRepository {

    public BusTicketNew findById(int id){
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .get(BusTicketNew.class, id);
    }

    public void save(BusTicketNew ticket){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }

    public void update(BusTicketNew ticket){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(ticket);
        transaction.commit();
        session.close();
    }

    public void delete(BusTicketNew ticket){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(ticket);
        transaction.commit();
        session.close();
    }
}
