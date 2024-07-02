package andersen.AndersenTasks.db_services;

import java.sql.*;

import andersen.AndersenTasks.busticket.BusTicketType;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class TicketServiceDAO {
    private Connection connection;

    public TicketServiceDAO(){
        setDBConnection();
    }

    private void setDBConnection() {
        try {
            PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
            propertiesConfiguration.load("application.properties");
            this.connection = DriverManager.getConnection(
                    propertiesConfiguration.getString("db.url"),
                    propertiesConfiguration.getString("db.username"),
                    propertiesConfiguration.getString("db.password"));

        } catch (SQLException exception){
            System.out.println("Something was wrong with DB Connection: "
                    + exception.getSQLState() + " "
                    + exception.getMessage()
            );
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTicket(BusTicketNew ticket) throws SQLException {
        String sql = "INSERT INTO ticket (id, user_id, ticket_type, creation_date, price) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setInt(1, ticket.getId());
        preparedStatement.setInt(2, ticket.getUser().getId());
        preparedStatement.setObject(3, ticket.getTicketType().toString(), Types.OTHER);
        preparedStatement.setTimestamp(4, Timestamp.valueOf(ticket.getStartDate().atStartOfDay()));
        preparedStatement.setInt(5, ticket.getPrice());

        preparedStatement.executeUpdate();

    }

    public void saveUser(UserNew user) throws SQLException{
        String sql = "INSERT INTO public.user (id, name, creation_date) VALUES (?,?,?)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(user.getStartDate().atStartOfDay()));

        preparedStatement.executeUpdate();
    }

    public BusTicketNew getTicketById(int ticket_id) throws SQLException{
        String sql = "SELECT * from ticket where id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setInt(1, ticket_id);
        return getTicketFromDB(preparedStatement.executeQuery());
    }
    public BusTicketNew getTicketByUserId(int userId) throws SQLException{
        String sql = "SELECT * from ticket where user_id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        return getTicketFromDB(preparedStatement.executeQuery());
    }

    private BusTicketNew getTicketFromDB(ResultSet resultSet) throws SQLException {
        BusTicketNew ticket = null;
        while (resultSet.next()) {
            ticket = new BusTicketNew(
                    resultSet.getInt("id"),
                    resultSet.getTimestamp("creation_date").toLocalDateTime().toLocalDate(),
                    BusTicketType.valueOf(resultSet.getString("ticket_type")),
                    resultSet.getInt("price"),
                    getUserById(resultSet.getInt("user_id"))
            );
        }
        return ticket;
    }

    public UserNew getUserById(int userId)throws SQLException {
        String sql = "SELECT * from public.user where id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        return getUserFromDB(preparedStatement.executeQuery());
    }

    private UserNew getUserFromDB(ResultSet resultSet) throws SQLException {
        UserNew user = null;
        while (resultSet.next()) {
            user = new UserNew(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getTimestamp("creation_date").toLocalDateTime().toLocalDate()
            );
        }
        return user;
    }

    public void updateTicketType(BusTicketType ticketType, BusTicketNew ticket) throws SQLException {
        String sql = "UPDATE ticket SET ticket_type = ? WHERE id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setObject(1, ticketType.toString(), Types.OTHER);
        preparedStatement.setInt(2, ticket.getId());

        preparedStatement.executeUpdate();
    }

    public void deleteUserById(int userId) throws SQLException{

        String sql = "DELETE FROM public.ticket where user_id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        preparedStatement.executeUpdate();

        String sql2 = "DELETE FROM public.user where id = ?";
        PreparedStatement preparedStatement2 = this.connection.prepareStatement(sql2);
        preparedStatement2.setInt(1, userId);
        preparedStatement2.executeUpdate();
    }
}
