package andersen.AndersenTasks.user;

import andersen.AndersenTasks.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Client extends User {

    private Ticket ticket;
    private Role role;

    public Client(Ticket ticket){
        super();
        this.role = Role.CLIENT;
    }
    @Override
    public void printRole(){
        super.printRole();
        System.out.println("My role is " + this.getRole());
    }


}
