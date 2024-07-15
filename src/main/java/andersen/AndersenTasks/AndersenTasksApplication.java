package andersen.AndersenTasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"andersen.AndersenTasks"})
@EnableConfigurationProperties
@SpringBootApplication
public class AndersenTasksApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AndersenTasksApplication.class, args);

		if (context.containsBean("thisIsMyFirstConditionalBean")) {
			System.out.println(context.getBean("thisIsMyFirstConditionalBean"));
		} else {
			System.out.println("Conditional bean isn't available ");
		}

	}

}
