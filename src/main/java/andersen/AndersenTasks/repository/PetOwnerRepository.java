package andersen.AndersenTasks.repository;

import andersen.AndersenTasks.models.PetOwner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PetOwnerRepository {
    private final DataSource dataSource;

    public PetOwnerRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public void save(PetOwner petOwner) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO pet_owners (owner_id, name, last_visit_date) VALUES (?,?,?)")) {
                preparedStatement.setLong(1, petOwner.getId());
                preparedStatement.setString(2, petOwner.getName());
                preparedStatement.setTimestamp(3, petOwner.getLastVisitDate());

                preparedStatement.executeUpdate();
            }
        }
    }
}
