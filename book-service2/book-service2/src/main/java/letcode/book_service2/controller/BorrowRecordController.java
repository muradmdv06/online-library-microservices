package letcode.book_service2.controller;

import letcode.book_service2.dto.BorrowRequestDto;
import letcode.book_service2.dto.BorrowResponseDto;
import letcode.book_service2.entity.BorrowRecord;
import letcode.book_service2.repository.BorrowRecordRepo;
import letcode.book_service2.service.BorrowRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow-records")
@RequiredArgsConstructor
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    @PostMapping("/borrow")
    public BorrowResponseDto borrow(@RequestBody BorrowRequestDto request) {
        return borrowRecordService.borrowBook(request);
    }

    @PostMapping("/return/{id}")
    public BorrowResponseDto returnBook(@PathVariable Long id) {
        return borrowRecordService.returnBook(id);
    }

    @GetMapping("/user/{userId}")
    public List<BorrowResponseDto> getUserRecords(@PathVariable Long userId) {
        return borrowRecordService.getUserRecords(userId);
    }

    @GetMapping
    public List<BorrowResponseDto> getAll() {
        return borrowRecordService.getAllRecords();
    }
}
