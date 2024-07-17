package andersen.AndersenTasks.service;

import andersen.AndersenTasks.busticket.BusTicketType;
import andersen.AndersenTasks.models.BusTicket;
import andersen.AndersenTasks.repository.BusTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BusTicketService_ {
    @Autowired
    private BusTicketRepository repository;


    public void saveBusTicket(BusTicket busTicket){
        repository.save(busTicket);
    }

    public Optional<BusTicket> getTicketById(Long id){
        return repository.findById(id);
    }

    public List<BusTicket> getTicketByUserId(Long user_id){
        return repository.findAllByUserId(user_id);
    }

    public BusTicket updateTicketType(BusTicket busTicket, BusTicketType type){
        Optional<BusTicket> busTicketForUpdate = repository.findById(busTicket.getId());
        if (busTicketForUpdate.isPresent()){
            busTicketForUpdate.get().setTicketType(type);
            BusTicket updatedBusTicket = repository.save(busTicketForUpdate.get());
            return updatedBusTicket;
        }
        return null;
    }
}
