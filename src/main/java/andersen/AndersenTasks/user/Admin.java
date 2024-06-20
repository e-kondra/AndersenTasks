package andersen.AndersenTasks.user;

import andersen.AndersenTasks.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User {
    private Role role;

    @Override
    public void printRole() {
        super.printRole();
        System.out.println("My role is Admin");
    }

    public void checkTicket(Ticket ticket){
        System.out.println("Checking ticket: " + ticket);
    }

}
