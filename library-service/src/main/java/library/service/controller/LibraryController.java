package library.service.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import library.service.model.BookStatus;
import library.service.payload.response.MessageResponse;
import library.service.service.BookStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@RestController
@RequestMapping("/library")
@Tag(name = "Library Controller", description = "API endpoints for managing library books")
public class LibraryController {
    @Autowired
    private BookStatusService statusService;

    @PostMapping("")
    @Operation(
            summary = "Add a Book",
            description = "Adds a book to the library."
    )
    public ResponseEntity<MessageResponse> addBook(@RequestBody @Parameter(description = "Book ID") long bookId){
        statusService.addBook(bookId);
        return new ResponseEntity<>(new MessageResponse("Successfully added"), HttpStatus.OK);
    }

    @GetMapping("/free")
    @Operation(
            summary = "Get Free Books",
            description = "Retrieves all the free books in the library."
    )
    public ResponseEntity<List<BookStatus>> getFreeBooks(){
        return new ResponseEntity<>(statusService.getFreeBooks(), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a Book",
            description = "Deletes a book from the library by its ID."
    )
    public ResponseEntity<MessageResponse> deleteBook(@PathVariable @Parameter(description = "Book ID") String id){
        statusService.deleteBook(Long.parseLong(id));
        return new ResponseEntity<>(new MessageResponse("Successfully deleted"), HttpStatus.OK);
    }


    @PostMapping("/{id}")
    @Operation(
            summary = "Update a Book",
            description = "Updates the information of a book in the library."
    )
    public ResponseEntity<MessageResponse> updateBook(@PathVariable @Parameter(description = "Book ID") String id,
                                                      @RequestBody @Parameter(description = "Updated Book Information") BookStatus bookStatus){
        bookStatus.setId(Long.parseLong(id));
        statusService.save(bookStatus);
        return new ResponseEntity<>(new MessageResponse("Successfully saved"), HttpStatus.OK);
    }

    @GetMapping("/v3/api-docs")
    public ModelAndView getDocs(){
        return new ModelAndView("redirect:http://localhost:8082/v3/api-docs");
    }

}
