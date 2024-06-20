package andersen.AndersenTasks.user;

import andersen.AndersenTasks.abstraction.ProtoType;
import andersen.AndersenTasks.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class User extends ProtoType {

    private Role role;

    public void printRole (){
        System.out.println("Each user has a role");
    }

}
