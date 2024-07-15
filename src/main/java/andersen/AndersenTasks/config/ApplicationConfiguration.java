package andersen.AndersenTasks.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ApplicationConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "conditional",name = "property",havingValue = "true")
    public String thisIsMyFirstConditionalBean() {
        return "My First Conditional Bean";
    }

}


