package andersen.AndersenTasks.controller;

import andersen.AndersenTasks.models.BusTicket;
import andersen.AndersenTasks.service.BusTicketService_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bus_ticket")
public class BusTicketController {
    @Autowired
    BusTicketService_ busTicketService;



    @GetMapping(value = "/{bus_ticket_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity displayBusTicket(@PathVariable("bus_ticket_id") Long id){
        Optional<BusTicket> busTicket = busTicketService.getTicketById(id);
        if (!busTicket.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(busTicket.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity createBusTicket(BusTicket busTicket){
        busTicketService.saveBusTicket(busTicket);
        return new ResponseEntity<>(busTicket, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity displayBusTicketsByUser(@PathVariable("user_id") Long user_id){
        List<BusTicket> busTicketList = busTicketService.getTicketByUserId(user_id);
        return new ResponseEntity<>(busTicketList, HttpStatus.OK);
    }
}
