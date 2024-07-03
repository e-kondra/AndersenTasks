package andersen.AndersenTasks.hibernate;

import andersen.AndersenTasks.busticket.BusTicketType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tickets")
public class BusTicketNew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="start_date")
    private Timestamp startDate;
    @Column(name="ticket_type")
    private BusTicketType ticketType;
    @Column(name="price")
    private int price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserNew user;
}
