package ru.gb.springdemo.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.*;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {
  @Getter
  @Value("${application.max-allowed-books:1}")
  private int maxBook;

  // спринг это все заинжектит
  private final RepositoryBook repositoryBook;
  private final RepositoryReader repositoryReader;
  private final RepositoryIssue repositoryIssue;

  public Issue issue(IssueRequest request) {
    if (!repositoryBook.existsById(request.getBookId())) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (!repositoryReader.existsById(request.getReaderId())) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    //Пункт 2.1 расширить параметром, сколько книг может быть на руках у пользователя.

    if (repositoryIssue.findByReaderId(request.getReaderId()).size()+1 > maxBook){
      return null;
    }

    Issue issue = new Issue();
    issue.setId(request.getId());
    issue.setBookId(request.getBookId());
    issue.setReaderId(request.getReaderId());
    repositoryIssue.save(issue);
    return issue;
  }

  public Issue getIssueById(long id){
    return repositoryIssue.findById(id).get();
  }

  public List<Issue> getAll(){
    return repositoryIssue.findAll();
  }

  public String getBookNameById(Issue issue){
    return repositoryBook.findById(issue.getBookId()).get().getName();
  }

  public String getReaderNameById(Issue issue){
    return repositoryReader.findById(issue.getReaderId()).get().getName();
  }

  public String getReadersNameById(long id){
    return repositoryReader.findById(id).get().getName();
  }

  public List<Issue> getIssueByReaderId(long id){
    return repositoryIssue.findByReaderId(id);
  }


}
