package letcode.book_service2.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {
    private Long id;
    private String title;
    private String authorName;
    private String categoryName;

    @Column(nullable = false)
    private boolean available;
}
