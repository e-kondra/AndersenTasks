package andersen.AndersenTasks.busticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusTicket{

    private Long id;
    private LocalDate startDate;
    private BusTicketType ticketType;
    private int price;
}
