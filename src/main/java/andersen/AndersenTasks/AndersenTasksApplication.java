package andersen.AndersenTasks;

import andersen.AndersenTasks.annotation.NullableWarningProcessor;
import andersen.AndersenTasks.ticket.Ticket;
import andersen.AndersenTasks.service.TicketService;
import andersen.AndersenTasks.user.Admin;
import andersen.AndersenTasks.user.Client;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AndersenTasksApplication {

	public static void main(String[] args) {

		TicketService ticketService = new TicketService();
		//ticketService.getTicketStorage().forEach(System.out::println);

		Ticket ticket = new Ticket();
		ticket.setProtoId(12L);

		ticket.print();

		ticket.share("+37068361010");
		ticket.share("+37068361010","e-kondra@gmail.com");

		Admin admin = new Admin();
		admin.printRole();
		admin.checkTicket(ticket);

		Client client = new Client(ticket);
		client.printRole();
		client.getTicket();

		Ticket ticket1 = new Ticket();
		ticket1.setProtoId(12L);
		System.out.println(ticket.equals(ticket1));
		System.out.println(ticket.hashCode());
		System.out.println(ticket1.hashCode());

		NullableWarningProcessor.fieldCheck(ticket1);
	}

}
