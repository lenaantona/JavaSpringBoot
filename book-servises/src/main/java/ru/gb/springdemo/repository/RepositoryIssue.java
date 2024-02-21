package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.Issue;

import java.util.List;

public interface RepositoryIssue extends JpaRepository<Issue, Long> {
    public List<Issue> findByReaderId(Long id);

    public List<Issue> findByBookId(Long id);
}
