package andersen.AndersenTasks.db_services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNew {
    private int id;
    private String name;
    private LocalDate startDate;
}
