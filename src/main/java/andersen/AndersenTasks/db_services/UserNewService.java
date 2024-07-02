package andersen.AndersenTasks.db_services;

import java.sql.SQLException;
import java.util.Optional;

public class UserNewService {
    private TicketServiceDAO ticketServiceDAO;

    public UserNewService(){
        this.ticketServiceDAO = new TicketServiceDAO();
    }

    public void createUser(UserNew user){
        if (user != null)
        {
            try {
                ticketServiceDAO.saveUser(user);
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("User couldn't be saved, it is null");
        }
    }

    public Optional<UserNew> fetchUserById (int userId){
        Optional<UserNew> optionalUser = Optional.empty();
        try {
            optionalUser = Optional.of(ticketServiceDAO.getUserById(userId));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return optionalUser;
    }

    public void deleteUserById (int userId){
        try {
            ticketServiceDAO.deleteUserById(userId);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
