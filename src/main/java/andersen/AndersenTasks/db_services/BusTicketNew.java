package andersen.AndersenTasks.db_services;

import andersen.AndersenTasks.busticket.BusTicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusTicketNew{

    private int id;
    private LocalDate startDate;
    private BusTicketType ticketType;
    private int price;
    private UserNew user;
}
