package library.service.repository;

import library.service.model.BookStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookStatusRepository extends CrudRepository<BookStatus, Long> {
    List<BookStatus> getByStartTime(BookStatus o);
}
