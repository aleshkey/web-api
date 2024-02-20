package book.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "library-service", url = "http://localhost:8082/library")
public interface LibraryServiceClient {
    @RequestMapping(value = "", method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
    void addBook(@RequestBody Long id);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes= MediaType.APPLICATION_JSON_VALUE)
    Object deleteBook(@PathVariable Long id);
}
