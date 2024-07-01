package andersen.AndersenTasks.service;

import andersen.AndersenTasks.busticket.BusTicket;
import andersen.AndersenTasks.busticket.BusTicketType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BusTicketService {

    public ArrayList<BusTicket> busTicketStorage;

    public BusTicketService(){
        this.busTicketStorage = new ArrayList<>();
    }

    public BusTicket createBusTicketByDateAndType(LocalDate date, BusTicketType type){
        BusTicket busTicket = new BusTicket();
        busTicket.setTicketType(type);
        busTicket.setStartDate(date);
        return busTicket;
    }

    public void saveBusTicketInStorage(BusTicket busTicket){
        try{
            busTicketStorage.add(busTicket);
            System.out.println("Bus ticked saved successfully");
        } catch (RuntimeException e){
            e.getStackTrace();
        }
    }

    public Optional<BusTicket> getBusTicketById(Long id) {
        return busTicketStorage.stream().filter(t -> t.getId() != null && t.getId().equals(id)).findFirst();
    }

    public void removeBusTicketById(Long id){
        Optional<BusTicket> optionalBusTicket = getBusTicketById(id);
        if (optionalBusTicket.isPresent()){
            busTicketStorage.remove(optionalBusTicket.get());
            System.out.println("Bus ticket with id " + id + " removed successfully");
        } else{
            System.out.println("Bus ticket with id " + id + " is not found");
        }
    }

    /**
     * Search tickets by tickets type and price segment (the value of price within priceFrom to priceTo)
     * <p>
     *     This method select such tickets from bus ticket storage, that are of specified type
     *     and whose price is within the specified maximum and minimum values
     * </p>
     *
     * @param type      the type of tickets for which the tickets are to be selected
     * @param priceFrom the minimum value of price of searching tickets
     * @param priceTo   the maximum value of price of searching tickets
     *
     * @return a new ArrayList of selected tickets by type and price segment
    */
    public ArrayList<BusTicket> getTicketsByTypeAndPrice(BusTicketType type, int priceFrom, int priceTo){
        List<BusTicket> resultTickets = busTicketStorage.stream().filter(t ->
                        t.getTicketType().equals(type)
                        && (t.getPrice() >= priceFrom && t.getPrice() <= priceTo)
                    ).collect(Collectors.toList());

        return (ArrayList<BusTicket>) resultTickets;
    }
}
