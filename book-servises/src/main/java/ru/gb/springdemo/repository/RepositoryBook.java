package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.gb.springdemo.model.Book;

import java.util.List;
import java.util.Optional;

public interface RepositoryBook extends JpaRepository<Book, Long> {



}
