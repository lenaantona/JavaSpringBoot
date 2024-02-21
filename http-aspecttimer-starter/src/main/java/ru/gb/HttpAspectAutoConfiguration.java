package ru.gb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpAspectAutoConfiguration {

    @Bean
    LogtimeAspect logtimeAspect(){
        return new LogtimeAspect();
    }
}
