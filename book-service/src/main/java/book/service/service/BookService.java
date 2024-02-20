package book.service.service;

import book.service.client.LibraryServiceClient;
import book.service.error.BookNotExistException;
import book.service.model.Book;
import book.service.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryServiceClient libraryService;


    public List<Book> getAllBooks() {
        Iterable<Book> entitiesIterable = bookRepository.findAll();
        return StreamSupport.stream(entitiesIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Book getBookById(long id){
        return bookRepository.findById(id).orElseThrow(()->{return new BookNotExistException("Cant find the book");});
    }

    public Book getBookByISBN(String ISBN){
        return bookRepository.findByIsbn(ISBN).orElseThrow(()->{return new BookNotExistException("Cant find the book");});
    }

    @Transactional
    public void saveBook(Book book){
        bookRepository.save(book);
        libraryService.addBook(book.getId());
    }

    @Transactional
    public void deleteBook(long id){
        libraryService.deleteBook(id);
        bookRepository.deleteById(id);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }
}
