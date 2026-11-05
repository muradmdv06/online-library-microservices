package letcode.book_service2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequestDto {
    private String name;
    private String biography;
}
