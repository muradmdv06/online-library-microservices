package letcode.book_service2.dto;

import lombok.Data;

@Data
public class BorrowRequestDto {
    private Long bookId;
    private Long userId;
}
