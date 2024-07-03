package andersen.AndersenTasks.hibernate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserNew {
    @Id
    @Column(name="id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "start_date")
    private Timestamp startDate;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BusTicketNew> tickets;
}