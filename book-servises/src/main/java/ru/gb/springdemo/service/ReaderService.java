package ru.gb.springdemo.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.ReaderRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.RepositoryIssue;
import ru.gb.springdemo.repository.RepositoryReader;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final RepositoryReader repositoryReader;
    private final RepositoryIssue repositoryIssue;
    public Reader getReaderById(long id){
        return repositoryReader.findById(id).get();
    }

    public List<Reader> deleteReader(Long id){
        if (!repositoryReader.existsById(id)) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + id + "\"");
        }
        repositoryReader.delete(getReaderById(id));
        return List.copyOf(repositoryReader.findAll());
    }

    public Reader addReader(ReaderRequest request){
        Reader readerNew = new Reader();
        readerNew.setId((long) request.getId());
        readerNew.setName(request.getName());
        repositoryReader.save(readerNew);
        return readerNew;
    }
    public List<Issue> getIssueByReaderId(long id){
        return repositoryIssue.findByReaderId(id);
    }

    public List<Reader> getAll(){
        return repositoryReader.findAll();
    }

    @PostConstruct
    public void generateReader(){
        Reader r1 = new Reader();
        r1.setId(1L);
        r1.setName("Ivan");
        repositoryReader.save(r1);
        Reader r2 = new Reader();
        r2.setId(2L);
        r2.setName("Irina");
        repositoryReader.save(r2);
        Reader r3 = new Reader();
        r3.setId(3L);
        r3.setName("Inna");
        repositoryReader.save(r3);
    }

}
