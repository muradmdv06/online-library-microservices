package letcode.book_service2.service.impl;

import letcode.book_service2.client.UserClient;
import letcode.book_service2.dto.BorrowRequestDto;
import letcode.book_service2.dto.BorrowResponseDto;
import letcode.book_service2.dto.UserResponseDto;
import letcode.book_service2.entity.Book;
import letcode.book_service2.entity.BorrowRecord;
import letcode.book_service2.repository.BookRepo;
import letcode.book_service2.repository.BorrowRecordRepo;
import letcode.book_service2.service.BorrowRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowRecordServiceImpl implements BorrowRecordService {

    private final BorrowRecordRepo borrowRecordRepo;
    private final BookRepo bookRepo;



    @Override
    public BorrowResponseDto borrowBook(BorrowRequestDto request) {
        Book book = bookRepo.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available");
        }

        book.setAvailable(false);
        bookRepo.save(book);

        BorrowRecord record = BorrowRecord.builder()
                .book(book)
                .userId(request.getUserId())
                .borrowDate(LocalDateTime.now())
                .status(BorrowRecord.Status.BORROWED)
                .build();

        return toDto(borrowRecordRepo.save(record));
    }

    @Override
    public BorrowResponseDto returnBook(Long recordId) {
        BorrowRecord record = borrowRecordRepo.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setReturnDate(LocalDateTime.now());
        record.setStatus(BorrowRecord.Status.RETURNED);

        Book book = record.getBook();
        book.setAvailable(true);
        bookRepo.save(book);

        return toDto(borrowRecordRepo.save(record));
    }

    @Override
    public List<BorrowResponseDto> getUserRecords(Long userId) {
        return borrowRecordRepo.findByUserId(userId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<BorrowResponseDto> getAllRecords() {
        return borrowRecordRepo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private BorrowResponseDto toDto(BorrowRecord record) {
        return BorrowResponseDto.builder()
                .id(record.getId())
                .bookId(record.getBook().getId())
                .userId(record.getUserId())
                .borrowDate(record.getBorrowDate())
                .returnDate(record.getReturnDate())
                .status(record.getStatus())
                .build();
    }
}
