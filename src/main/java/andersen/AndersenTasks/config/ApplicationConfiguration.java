package andersen.AndersenTasks.config;

import andersen.AndersenTasks.repository.BusTicketRepository;
import andersen.AndersenTasks.repository.UserRepository;
import andersen.AndersenTasks.service.BusTicketService_;
import andersen.AndersenTasks.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ApplicationConfiguration {

    @Bean
    public BusTicketRepository BusTicketRepository(){
        return new BusTicketRepository();
    }

    @Bean
    public BusTicketService_ BusTicketService_(){
        return new BusTicketService_(BusTicketRepository());
    }

    @Bean
    public UserRepository UserRepository(){
        return new UserRepository();
    }

    @Bean
    public UserService UserService(){
        return new UserService(UserRepository());
    }

}


