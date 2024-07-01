package andersen.AndersenTasks;

import andersen.AndersenTasks.annotation.NullableWarningProcessor;
import andersen.AndersenTasks.busticket.BusTicket;
import andersen.AndersenTasks.busticket.BusTicketType;
import andersen.AndersenTasks.service.BusTicketService;
import andersen.AndersenTasks.service.TicketService;
import andersen.AndersenTasks.ticket.Ticket;
import andersen.AndersenTasks.user.Admin;
import andersen.AndersenTasks.user.Client;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
public class AndersenTasksApplication {

	public static void main(String[] args) {


		TicketService ticketService = new TicketService();

		BusTicketService busTicketService = new BusTicketService();
		busTicketService.busTicketStorage.add(busTicketService.createBusTicketByDateAndType(LocalDate.now(), BusTicketType.DAY));
		busTicketService.saveBusTicketInStorage(
				busTicketService.createBusTicketByDateAndType(LocalDate.of(2024, Month.JANUARY,12), BusTicketType.YEAR));
		busTicketService.busTicketStorage
				.add(busTicketService.createBusTicketByDateAndType(LocalDate.of(2024,6,18), BusTicketType.MONTH));
		busTicketService.busTicketStorage
				.add(new BusTicket(1L, LocalDate.of(2023,12,10), BusTicketType.YEAR, 200));
		busTicketService.saveBusTicketInStorage(
				new BusTicket(2L, LocalDate.of(2023,10,1), BusTicketType.YEAR, 6000));
		busTicketService.saveBusTicketInStorage(
				new BusTicket( 3L, LocalDate.of(2024,6,17), BusTicketType.WEEK, 1200));

		busTicketService.busTicketStorage.forEach(System.out::println);

		Optional<BusTicket> busTicket = busTicketService.getBusTicketById(1L);
		System.out.println("busTicket get by id = 1 " + busTicket.get());

		busTicketService.removeBusTicketById(2L);
		System.out.println("---- remove ticket with id = 2");
		busTicketService.busTicketStorage.forEach(System.out::println);

		ArrayList<BusTicket> tickets = busTicketService.getTicketsByTypeAndPrice(BusTicketType.YEAR, 0, 1000);
		System.out.println("---- getTicketsByTypeAndPrice. TYPE=YEAR, price = from 0 to 1000");
		tickets.forEach(System.out::println);


	}

}
