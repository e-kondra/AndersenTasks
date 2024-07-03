package andersen.AndersenTasks.hibernate;

import andersen.AndersenTasks.busticket.BusTicketType;

import java.util.List;

public class BusTicketService {
    private BusTicketRepository repository = new BusTicketRepository();

    public BusTicketService(){}

    public void saveBusTicket(BusTicketNew busTicket){
        repository.save(busTicket);
    }
    public BusTicketNew getTicketById(int id){
        return repository.fetchById(id);
    }

    public List<BusTicketNew> getTicketByUserId(int id){
        return repository.fetchByUserId(id);
    }

    public void updateTicketType(BusTicketNew busTicket, BusTicketType type){
        repository.updateTicketType(busTicket, type);
    }
}
