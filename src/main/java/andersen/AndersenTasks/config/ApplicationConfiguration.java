package andersen.AndersenTasks.config;

import andersen.AndersenTasks.repository.PetOwnerRepository;
import andersen.AndersenTasks.repository.PetRepository;
import andersen.AndersenTasks.service.PetOwnerService;
import andersen.AndersenTasks.service.PetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@ComponentScan
@PropertySources({@PropertySource("classpath:application.properties")})
public class ApplicationConfiguration {

    @Autowired
    private Environment env;

    public ApplicationConfiguration(){}

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driver-class-name", "org.postgresql.Driver"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }


    @Bean
    public PetRepository PetRepository(){
        System.out.println(dataSource());
        return new PetRepository(dataSource());
    }

    @Bean
    public PetService PetService(){
        return new PetService(PetRepository());
    }

    @Bean
    public PetOwnerRepository PetOwnerRepository(){
        return new PetOwnerRepository();
    }

    @Bean
    public PetOwnerService PetOwnerService(){
        return new PetOwnerService();
    }
}
