package ru.gb.springdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.ReaderService;

import java.util.Collections;
import java.util.List;

//1.2 /ui/readers - аналогично 1.1
@Controller
@RequestMapping("/ui")
public class ReaderHtmlController {
    @Autowired
    private ReaderService service;
    @GetMapping("/readers")
    public String table(Model model) {
        List<Reader> readers = List.copyOf(service.getAll());
        model.addAttribute("readers", readers);
        return "readers";
    }

}
