package letcode.book_service2.service;

import letcode.book_service2.dto.BorrowRequestDto;
import letcode.book_service2.dto.BorrowResponseDto;
import letcode.book_service2.entity.BorrowRecord;

import java.util.List;

public interface BorrowRecordService {
    BorrowResponseDto borrowBook(BorrowRequestDto request);

    BorrowResponseDto returnBook(Long recordId);

    List<BorrowResponseDto> getUserRecords(Long userId);

    List<BorrowResponseDto> getAllRecords();
}
