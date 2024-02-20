package book.service.controller;

import book.service.model.Book;
import book.service.payload.response.MessageResponse;
import book.service.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Book Controller", description = "Controller for managing books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("")
    @Operation(summary = "Get all books", description = "Retrieve information about all books")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of books",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Book.class, type = "array")))
    public ResponseEntity<List<Book>> getAll(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieve information about a book by its ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of the book",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Book.class)))
    public ResponseEntity<Book> getById(@PathVariable @Parameter(description = "Book ID") String id){
        return new ResponseEntity<>(bookService.getBookById(Long.parseLong(id)), HttpStatus.OK);
    }

    @GetMapping("/isbn/{isbn}")
    @Operation(summary = "Get book by ISBN", description = "Retrieve information about a book by its ISBN")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of the book",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Book.class)))
    public ResponseEntity<Book> getByISBN(@PathVariable @Parameter(description = "Book isbn") String isbn){
        return new ResponseEntity<>(bookService.getBookByISBN(isbn), HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(summary = "Add a book", description = "Add information about a new book")
    @ApiResponse(responseCode = "200", description = "Book added successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageResponse.class)))
    public ResponseEntity<MessageResponse> addBook(@RequestBody @Parameter(description = "Book information",
            schema = @Schema(implementation = Book.class)) Book book){
        bookService.saveBook(book);
        return new ResponseEntity<>(new MessageResponse("Successfully added"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Delete a book by its ID")
    @ApiResponse(responseCode = "200", description = "Book deleted successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageResponse.class)))
    public ResponseEntity<MessageResponse> deleteBook(@PathVariable @Parameter(description = "Book ID") String id){
        bookService.deleteBook(Long.parseLong(id));
        return new ResponseEntity<>(new MessageResponse("Successfully deleted"), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Update a book", description = "Update information about a book")
    @ApiResponse(responseCode = "200", description = "Book updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageResponse.class)))
    public ResponseEntity<MessageResponse> updateBook(@PathVariable @Parameter(description = "Book ID") String id,
                                                      @RequestBody @Parameter(description = "Book info") Book book){
        book.setId(Long.parseLong(id));
        bookService.updateBook(book);
        return new ResponseEntity<>(new MessageResponse("Successfully updated"), HttpStatus.OK);
    }


}

