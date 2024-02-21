package ru.gb.springdemo.service;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.BookRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.RepositoryBook;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {

    private final RepositoryBook repositoryBook;

    public Book getBookById(long id){
        return repositoryBook.findById(id).get();
    }

    public List<Book> deleteBook(Long id){
        if (!repositoryBook.existsById(id)) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + id + "\"");
        }
        repositoryBook.delete(getBookById(id));
        return repositoryBook.findAll();
    }

    public Book addBook(BookRequest request){
        Book bookNew = new Book();
        bookNew.setId((long) request.getId());
        bookNew.setName(request.getName());
        repositoryBook.save(bookNew);
        return bookNew;
    }
    public List<Book> getAll(){
        return repositoryBook.findAll();
    }

    @PostConstruct
    public void generateBook(){
        Book b1 = new Book();
        b1.setId(1L);
        b1.setName("Война и мир");
        repositoryBook.save(b1);
        Book  b2 = new Book();
        b2.setId(2L);
        b2.setName("Анна Каренина");
        repositoryBook.save(b2);
        Book b3 = new Book();
        b3.setId(3L);
        b3.setName("Мертвые души");
        repositoryBook.save(b3);
    }

}
