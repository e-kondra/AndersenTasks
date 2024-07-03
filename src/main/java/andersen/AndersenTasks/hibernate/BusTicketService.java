package andersen.AndersenTasks.hibernate;

public class BusTicketService {
    private BusTicketRepository repository = new BusTicketRepository();

    public BusTicketService(){}

    public void saveBusTicket(BusTicketNew busTicket){
        repository.save(busTicket);
    }

}
