package andersen.AndersenTasks.hibernate;

import andersen.AndersenTasks.busticket.BusTicketType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BusTicketRepository {

    public BusTicketNew fetchById(int id){
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .get(BusTicketNew.class, id);
    }

    public List<BusTicketNew> fetchByUserId(int userId){
        UserNew user = SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .find(UserNew.class, userId);
        return new ArrayList<>(user.getTickets());
    }

    public void save(BusTicketNew ticket){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }

    public void updateTicketType(BusTicketNew ticketNew, BusTicketType type){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        BusTicketNew ticket = fetchById(ticketNew.getId());
        ticket.setTicketType(type);
        session.merge(ticket);
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
