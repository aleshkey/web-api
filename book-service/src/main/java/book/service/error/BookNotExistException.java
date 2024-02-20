package book.service.error;

public class BookNotExistException extends RuntimeException{
    public BookNotExistException(String message) {
        super(message);
    }
}