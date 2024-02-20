package library.service.service;

import library.service.model.BookStatus;
import library.service.repository.BookStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStatusService {
    @Autowired
    private BookStatusRepository statusRepository;

    public void addBook(long bookId) {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setId(bookId);
        statusRepository.save(bookStatus);
    }

    public List<BookStatus> getFreeBooks(){
        return statusRepository.getByStartTime(null);
    }

    public void deleteBook(long id) {
        statusRepository.deleteById(id);
    }

    public void save(BookStatus bookStatus) {
        statusRepository.save(bookStatus);
    }
}

