package andersen.AndersenTasks.service;

import andersen.AndersenTasks.busticket.BusTicketType;
import andersen.AndersenTasks.models.BusTicket;
import andersen.AndersenTasks.repository.BusTicketRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusTicketService_ {

    private BusTicketRepository repository;

    public BusTicketService_(BusTicketRepository busTicketRepository){
        this.repository = busTicketRepository;
    }

    public void saveBusTicket(BusTicket busTicket){
        repository.save(busTicket);
    }
    public BusTicket getTicketById(int id){
        return repository.fetchById(id);
    }

    public List<BusTicket> getTicketByUserId(int id){
        return repository.fetchByUserId(id);
    }

    public void updateTicketType(BusTicket busTicket, BusTicketType type){
        repository.updateTicketType(busTicket, type);
    }
}
