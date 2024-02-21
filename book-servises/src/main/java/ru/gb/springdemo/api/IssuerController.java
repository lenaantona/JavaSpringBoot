package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssuerService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
@Tag(name = "Issue")
public class IssuerController {

  @Autowired
  private IssuerService service;

//  @PutMapping
//  public void returnBook(long issueId) {
//    // найти в репозитории выдачу и проставить ей returned_at
//  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "add issue", description = "Добавляет выдачу книги")
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
    if (issue != null) return ResponseEntity.status(HttpStatus.OK).body(issue);
    //ResponseEntity.status(HttpStatus.CONFLICT).body("У читателя не может быть больше "+service.getMaxBook()+" книг на руках");
  else {throw new ResponseStatusException(HttpStatus.CONFLICT, "У читателя не может быть больше книг на руках");
   }
  }
  //* 1.3 В контроллере IssueController добавить ресурс GET /issue/{id} - получить описание факта выдачи
  @GetMapping(value = "/{id}")
  @Operation(summary = "get issue by id", description = "Загружает выдачу по заданному id")
  public Issue getIssue(@PathVariable long id) {
    return service.getIssueById(id);
  }

}
