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
import ru.gb.springdemo.service.BookService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@Tag(name = "Book")
public class BookController {

 //1.1 Реализовать контроллер по управлению книгами с ручками: GET /book/{id} - получить описание книги,
 // DELETE /book/{id} - удалить книгу, POST /book - создать книгу
    @Autowired
    private BookService service;

    @Logtime(level = Level.INFO)
    @GetMapping(value = "/{id}")
    @Operation(summary = "get book by id", description = "Загружает книгу по заданному id")
    public Book getBookById(@PathVariable long id) {
        return service.getBookById(id);
    }

    //DELETE /book/{id} - удалить студента(отладиться можно с помощью Postman)
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "delete book by id", description = "Удаляет книгу по заданному id")
    public List<Book> delete(@PathVariable long id) {
      return service.deleteBook(id);
    }

    @PostMapping
    @Operation(summary = "add book", description = "Добавляет новую книгу")
    public ResponseEntity<Book> addBook(@RequestBody BookRequest request) {
       final Book bookreturn;
        try {
            bookreturn = service.addBook(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookreturn);
    }


}
