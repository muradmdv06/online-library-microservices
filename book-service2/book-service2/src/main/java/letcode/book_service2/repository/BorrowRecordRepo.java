package letcode.book_service2.repository;

import letcode.book_service2.dto.BorrowRequestDto;
import letcode.book_service2.dto.BorrowResponseDto;
import letcode.book_service2.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepo extends JpaRepository<BorrowRecord,Long> {
    List<BorrowRecord> findByUserId(Long userId);
}
