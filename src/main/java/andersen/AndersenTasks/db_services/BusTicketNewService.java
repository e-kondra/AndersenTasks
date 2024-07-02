package andersen.AndersenTasks.db_services;

import andersen.AndersenTasks.busticket.BusTicketType;

import java.sql.SQLException;
import java.util.Optional;

public class BusTicketNewService {

    private TicketServiceDAO ticketServiceDAO;

    public BusTicketNewService(){
        this.ticketServiceDAO = new TicketServiceDAO();
    }

    public void createTicket(BusTicketNew ticket){
        if (ticket != null)
        {
            try {
                ticketServiceDAO.saveTicket(ticket);
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Ticket couldn't be saved, it is null");
        }
    }


    public Optional<BusTicketNew> fetchTicketById (int id){
        Optional<BusTicketNew> optionalTicket = Optional.empty();
        try {
            optionalTicket = Optional.of(ticketServiceDAO.getTicketById(id));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return optionalTicket;
    }

    public Optional<BusTicketNew> fetchTicketByUserId (int userId){
        Optional<BusTicketNew> optionalTicket = Optional.empty();
        try {
            optionalTicket = Optional.of(ticketServiceDAO.getTicketByUserId(userId));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return optionalTicket;
    }


    public void updateTicketType (BusTicketType ticketType, BusTicketNew ticket){
        try {
            ticketServiceDAO.updateTicketType(ticketType, ticket);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}
