package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.Logtime;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reader")
@Tag(name = "Reader")
public class ReaderController {

    // * 1.2 Реализовать контроллер по управлению читателями (аналогично контроллеру с книгами из 1.1)
    @Autowired
    private ReaderService service;

    @Logtime(level = Level.INFO)
    @GetMapping(value = "/{id}")
    @Operation(summary = "get reader by id", description = "Загружает читателя по заданному id")
    public Reader getReaderById(@PathVariable long id) {
        return service.getReaderById(id);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "delete reader by id", description = "Удаляет читателя по заданному id")
    public List<Reader> delete(@PathVariable long id) {
        return service.deleteReader(id);
    }

    @PostMapping
    @Operation(summary = "add reader", description = "Добавляет нового читателя")
    public ResponseEntity<Reader> issueBook(@RequestBody ReaderRequest reader) {
        final Reader readerreturn;
        try {
            readerreturn = service.addReader(reader);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(readerreturn);
    }

     //* 2.2 В сервис читателя добавить ручку GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя
    @GetMapping(value = "/{id}/issue")
    @Operation(summary = "get all  issue by reader_id", description = "Загружает все выдачи для данного читателя")
    public List<Issue> getIssueById(@PathVariable long id) {
        return service.getIssueByReaderId(id);
    }
}
