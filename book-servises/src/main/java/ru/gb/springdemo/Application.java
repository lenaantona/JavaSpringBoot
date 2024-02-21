package ru.gb.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.RepositoryBook;
import ru.gb.springdemo.repository.RepositoryReader;

@SpringBootApplication
public class Application {

public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
}

}
