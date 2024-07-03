package andersen.AndersenTasks.hibernate;

import andersen.AndersenTasks.busticket.BusTicketType;

import java.sql.Timestamp;
import java.time.LocalDate;

public class HibernateMain {
    public static void main(String[] args) {

        BusTicketService busTicketService = new BusTicketService();

        UserNew user = new UserNew(1, "Luna", Timestamp.valueOf(LocalDate.now().atStartOfDay()), null);


        //BusTicketNew busTicketNew = new BusTicketNew(4, Timestamp.valueOf(LocalDate.now().atStartOfDay()), BusTicketType.MONTH, 220,
         //       new UserNew(4, "Luna", Timestamp.valueOf(LocalDate.now().atStartOfDay()), null));
       // busTicketService.saveBusTicket(busTicketNew);
    }
}
