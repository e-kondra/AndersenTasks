package andersen.AndersenTasks.ticket;

public class TicketValidationException extends RuntimeException{
    public TicketValidationException(String message){
        super(message);
    }
}
