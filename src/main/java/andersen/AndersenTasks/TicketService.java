package andersen.AndersenTasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class TicketService {

    private final ArrayList<Ticket> ticketStorage;

    public TicketService() {
        this.ticketStorage = this.fillInStorage();
    }

    public ArrayList<Ticket> getTicketStorage() {
        return ticketStorage;
    }

    private ArrayList<Ticket> fillInStorage() {
        ArrayList tickets = new ArrayList<>();
        for(int i = 1; i<11 ;i++){
            Ticket ticket = new Ticket(Long.valueOf(i), "Compensa",
                    342, false,
                    Sector.values()[new Random().nextInt(Sector.values().length)],
                    2.300f, new BigDecimal(23.56));
            tickets.add(ticket);
        }
        return tickets;
    }

    public static void main(String[] args) {
        TicketService ticketService = new TicketService();
        ticketService.getTicketStorage().forEach(System.out::println);

        Ticket ticket = new Ticket();
        ticket.setProtoId(12L);

        ticket.print();

        ticket.share("+37068361010");
        ticket.share("+37068361010","e-kondra@gmail.com");

    }

}
