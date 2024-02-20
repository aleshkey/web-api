package library.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class BookStatus {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime finishTime;

}
