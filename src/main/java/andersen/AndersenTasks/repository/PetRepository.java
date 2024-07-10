package andersen.AndersenTasks.repository;

import andersen.AndersenTasks.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import static java.sql.Timestamp.*;


@Component
@PropertySources({@PropertySource("classpath:application.properties")})
public class PetRepository {
    private final DataSource dataSource;
    @Autowired
    private Environment env;

    public PetRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Transactional
    public void save(Pet pet) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO pets (pet_id, name, birth_date, owner_id) VALUES (?,?,?,?)")) {
                preparedStatement.setLong(1, pet.getId());
                preparedStatement.setString(2, pet.getName());
                preparedStatement.setTimestamp(3, pet.getBirthDate());
                preparedStatement.setLong(4, pet.getOwner().getId());

                preparedStatement.executeUpdate();
            }
            if (env.getProperty("custom-property.update-pet-Owner", "false").equals("true")){
                try (PreparedStatement preparedStatement = connection
                        .prepareStatement("UPDATE pet_owners SET last_visit_date = ? WHERE owner_id = ?;")) {
                    preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
                    preparedStatement.setLong(2, pet.getOwner().getId());
                    preparedStatement.executeUpdate();
                }
            } else {
                throw new RuntimeException("Pet Owner couldn't be updated");
            }
        }
    }

    @Transactional
    public void update(Pet pet) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE pets SET pet_id = ?, name = ?, birth_date = ?, owner_id = ? WHERE pet_id = ?;")) {
                preparedStatement.setLong(1, pet.getId());
                preparedStatement.setString(2, pet.getName());
                preparedStatement.setTimestamp(3, pet.getBirthDate());
                preparedStatement.setLong(4, pet.getOwner().getId());
                preparedStatement.setLong(5, pet.getId());

                preparedStatement.executeUpdate();
            }

            try (PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE pet_owners SET last_visit_date = ? WHERE owner_id = ?;")) {
                preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
                preparedStatement.setLong(2, pet.getOwner().getId());
                preparedStatement.executeUpdate();
            }
        }
    }
}
