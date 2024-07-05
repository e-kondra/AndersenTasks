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


	}

}
