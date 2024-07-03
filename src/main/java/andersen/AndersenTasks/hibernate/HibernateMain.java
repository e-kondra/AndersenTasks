package andersen.AndersenTasks.hibernate;

import andersen.AndersenTasks.busticket.BusTicketType;

import java.sql.Timestamp;
import java.time.LocalDate;

public class HibernateMain {
    public static void main(String[] args) {

        BusTicketService busTicketService = new BusTicketService();
        UserService userService = new UserService();

        UserNew user = new UserNew(1, "Luna", Timestamp.valueOf(LocalDate.now().atStartOfDay()), null);
        UserNew user1 = new UserNew(2, "Iriska", Timestamp.valueOf(LocalDate.now().atStartOfDay()), null);
        UserNew user2 = new UserNew(3, "Monika", Timestamp.valueOf(LocalDate.now().atStartOfDay()), null);
        userService.saveUser(user);
        userService.saveUser(user1);
        userService.saveUser(user2);


        BusTicketNew busTicketNew = new BusTicketNew(1, Timestamp.valueOf(LocalDate.now().atStartOfDay())
                , BusTicketType.MONTH, 220, user);
        BusTicketNew busTicketNew2 = new BusTicketNew(2, Timestamp.valueOf(LocalDate.now().atStartOfDay())
                , BusTicketType.WEEK, 51, user2);
        BusTicketNew busTicketNew3 = new BusTicketNew(3, Timestamp.valueOf(LocalDate.now().atStartOfDay())
                , BusTicketType.DAY, 51, user2);

        busTicketService.saveBusTicket(busTicketNew);
        busTicketService.saveBusTicket(busTicketNew2);
        busTicketService.saveBusTicket(busTicketNew3);

        System.out.println(userService.getUserById(2));
        System.out.println(busTicketService.getTicketById(1));

        busTicketService.getTicketByUserId(3).forEach(System.out::println);

        busTicketService.updateTicketType(busTicketNew3, BusTicketType.YEAR);
        System.out.println(busTicketService.getTicketById(3));


        userService.deleteUserById(3);

    }
}
