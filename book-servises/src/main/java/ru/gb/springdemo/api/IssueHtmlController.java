package ru.gb.springdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.IssuerService;

import java.util.Collections;
import java.util.List;

//  1.3 /ui/issues - на странице отображается таблица, в которой есть столбцы
//  (книга, читатель, когда взял, когда вернул (если не вернул - пустая ячейка)).
@Controller
@RequestMapping("/ui")
public class IssueHtmlController {
    @Autowired
    private IssuerService service;
    @GetMapping("/issues")
    public String table(Model model) {
        IssuerService issues = service;
        model.addAttribute("issues", issues);
        return "issues";
    }
    //1.4* /ui/reader/{id} - страница, где написано имя читателя с идентификатором id и перечислены книги, которые на руках у этого читателя
    @GetMapping(value = "/reader/{id}")
    public String table(Model model, @PathVariable long id) {
        IssuerService reader = service;
        model.addAttribute("reader", reader);
        model.addAttribute("id", id);
        return "reader";
    }
}
