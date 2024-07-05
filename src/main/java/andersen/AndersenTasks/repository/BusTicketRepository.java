package andersen.AndersenTasks.repository;

import andersen.AndersenTasks.busticket.BusTicketType;
import andersen.AndersenTasks.models.BusTicket;
import andersen.AndersenTasks.models.User;
import andersen.AndersenTasks.util.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BusTicketRepository {

    public BusTicket fetchById(int id){
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .get(BusTicket.class, id);
    }

    public List<BusTicket> fetchByUserId(int userId){
        User user = SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .find(User.class, userId);
        return new ArrayList<>(user.getTickets());
    }

    public void save(BusTicket ticket){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }

    public void updateTicketType(BusTicket ticketNew, BusTicketType type){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        BusTicket ticket = fetchById(ticketNew.getId());
        ticket.setTicketType(type);
        session.merge(ticket);
        transaction.commit();
        session.close();
    }

    public void delete(BusTicket ticket){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(ticket);
        transaction.commit();
        session.close();
    }
}
