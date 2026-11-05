package letcode.book_service2.repository;

import letcode.book_service2.entity.Author;
import letcode.book_service2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    List<Category> findByName(
            String name
    );
}
