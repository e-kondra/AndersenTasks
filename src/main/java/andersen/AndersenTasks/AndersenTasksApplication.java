package andersen.AndersenTasks;

import andersen.AndersenTasks.config.ApplicationConfiguration;
import andersen.AndersenTasks.models.Pet;
import andersen.AndersenTasks.models.PetOwner;
import andersen.AndersenTasks.service.PetOwnerService;
import andersen.AndersenTasks.service.PetService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
public class AndersenTasksApplication {

	public static void main(String[] args) {
		var applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

		PetService petService = applicationContext.getBean(PetService.class);
		PetOwnerService petOwnerService = applicationContext.getBean(PetOwnerService.class);

		PetOwner petOwner = new PetOwner(1L, "John Silver", Timestamp.valueOf(LocalDate.now().atStartOfDay()), null);
		petOwnerService.savePetOwner(petOwner);
		Pet pet = new Pet(1l, "Monika", Timestamp.valueOf(LocalDate.of(2015,11,23).atStartOfDay()), petOwner);
		petService.savePet(pet);
		Pet pet1 = new Pet(6l, "Alisa", Timestamp.valueOf(LocalDate.of(2022,5,1).atStartOfDay()), petOwner);
		petService.savePet(pet1);
		petService.updatePet(pet);

		try {
			List<PetOwner> petOwners = petOwnerService.getTicketsFromFile(
					applicationContext.getResource("classpath:test_data.txt").getFile());
			petOwners.forEach(System.out::println);
		}catch (Exception e){
			System.out.println(e);
		}

	}

}
