package letcode.book_service2.dto;

import letcode.book_service2.entity.BorrowRecord;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BorrowResponseDto {
    private Long id;
    private Long bookId;
    private Long userId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private BorrowRecord.Status status;
}
