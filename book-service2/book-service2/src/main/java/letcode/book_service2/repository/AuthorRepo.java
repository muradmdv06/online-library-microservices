package letcode.book_service2.repository;

import letcode.book_service2.entity.Author;
import letcode.book_service2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author,Long> {
    List<Author> findByName(
            String name
    );
}
