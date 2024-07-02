package andersen.AndersenTasks.db_services;

import andersen.AndersenTasks.busticket.BusTicketType;

import java.time.LocalDate;

public class TestServiceDAO {
    public static void main(String[] args) {

       BusTicketNewService busTicketNewService = new BusTicketNewService();
       UserNewService userNewService = new UserNewService();
        UserNew user = new UserNew(4,"Anna", LocalDate.now());
        userNewService.createUser(user);
        busTicketNewService.createTicket(new BusTicketNew(3, LocalDate.now(), BusTicketType.MONTH, 12, user));

        UserNew userNew = userNewService.fetchUserById(1).get();
        System.out.println(userNew);

        BusTicketNew ticketNew = busTicketNewService.fetchTicketById(2).get();
        System.out.println(ticketNew);

        busTicketNewService.updateTicketType(BusTicketType.DAY, busTicketNewService.fetchTicketById(2).get());
        System.out.println("updated Type" + busTicketNewService.fetchTicketById(2).get());

        userNewService.deleteUserById(3);
    }
}
