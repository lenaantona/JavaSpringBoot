package ru.gb.springdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

import java.util.List;

//  * 1.1 /ui/books - на странице должен отобразиться список всех доступных книг в системе
@Controller
@RequestMapping("/ui")
public class BookHtmlController {
    @Autowired
    private BookService service;
    @GetMapping("/books")
    public String table(Model model) {
        List<Book> books = List.copyOf(service.getAll());
        model.addAttribute("books", books);
        return "books";
    }
}
