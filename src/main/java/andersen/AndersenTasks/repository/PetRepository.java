package andersen.AndersenTasks.repository;

import andersen.AndersenTasks.models.Pet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Component
public class PetRepository {
    private final DataSource dataSource;
    public PetRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public void save(Pet pet) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO pets (pet_id, name, birth_date) VALUES (?,?,?)")) {
                preparedStatement.setLong(1, pet.getId());
                preparedStatement.setString(2, pet.getName());
                preparedStatement.setTimestamp(3, pet.getBirthDate());

                preparedStatement.executeUpdate();
            }
        }
    }
}
