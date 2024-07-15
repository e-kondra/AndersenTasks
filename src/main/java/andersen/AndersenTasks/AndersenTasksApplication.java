package andersen.AndersenTasks;


import andersen.AndersenTasks.busticket.BusTicketType;
import andersen.AndersenTasks.config.ApplicationConfiguration;
import andersen.AndersenTasks.models.BusTicket;
import andersen.AndersenTasks.models.User;
import andersen.AndersenTasks.service.BusTicketService_;
import andersen.AndersenTasks.service.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDate;

@SpringBootApplication
public class AndersenTasksApplication {

	public static void main(String[] args) {
		var applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		UserService userService = applicationContext.getBean(UserService.class);
		BusTicketService_ busTicketService = applicationContext.getBean(BusTicketService_.class);

		userService.saveUser(new User(10,"Alisa", Timestamp.valueOf(LocalDate.now().atStartOfDay()), null));

		User user = new User(1, "Luna", Timestamp.valueOf(LocalDate.now().atStartOfDay()), null);
		User user1 = new User(2, "Iriska", Timestamp.valueOf(LocalDate.now().atStartOfDay()), null);
		User user2 = new User(3, "Monika", Timestamp.valueOf(LocalDate.now().atStartOfDay()), null);
		userService.saveUser(user);
		userService.saveUser(user1);
		userService.saveUser(user2);


		BusTicket busTicketNew = new BusTicket(1, Timestamp.valueOf(LocalDate.now().atStartOfDay())
				, BusTicketType.MONTH, 220, user);
		BusTicket busTicketNew2 = new BusTicket(2, Timestamp.valueOf(LocalDate.now().atStartOfDay())
				, BusTicketType.WEEK, 51, user2);
		BusTicket busTicketNew3 = new BusTicket(3, Timestamp.valueOf(LocalDate.now().atStartOfDay())
				, BusTicketType.DAY, 51, user2);

		busTicketService.saveBusTicket(busTicketNew);
		busTicketService.saveBusTicket(busTicketNew2);
		busTicketService.saveBusTicket(busTicketNew3);

		System.out.println(userService.getUserById(2));
		System.out.println(busTicketService.getTicketById(1));

		busTicketService.getTicketByUserId(3).forEach(System.out::println);

		busTicketService.updateTicketType(busTicketNew3, BusTicketType.YEAR);
		System.out.println(busTicketService.getTicketById(3));


		userService.deleteUserById(3);




		TicketService ticketService = new TicketService();
		//ticketService.getTicketStorage().forEach(System.out::println);

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


//		TicketService ticketService = new TicketService();
		//ticketService.getTicketStorage().forEach(System.out::println);

//		Ticket ticket = new Ticket();
//		ticket.setProtoId(12L);
//
//		ticket.print();
//
//		ticket.share("+37068361010");
//		ticket.share("+37068361010","e-kondra@gmail.com");
//
//		Admin admin = new Admin();
//		admin.printRole();
//		admin.checkTicket(ticket);
//
//		Client client = new Client(ticket);
//		client.printRole();
//		client.getTicket();
//
//		Ticket ticket1 = new Ticket();
//		ticket1.setProtoId(12L);
//		System.out.println(ticket.equals(ticket1));
//		System.out.println(ticket.hashCode());
//		System.out.println(ticket1.hashCode());
//
//		NullableWarningProcessor.fieldCheck(ticket1);

	}

}
