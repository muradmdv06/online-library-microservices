package letcode.book_service2.service;

import letcode.book_service2.dto.BookRequestDto;
import letcode.book_service2.dto.BookResponseDto;
import letcode.book_service2.entity.Book;

import java.util.List;

public interface BookService {

    BookResponseDto create(BookRequestDto dto);

    List<BookResponseDto> getAllBooks();

    BookResponseDto getByAuthor(Long authorId);

    BookResponseDto getByCategory(Long categoryId);

    List<BookResponseDto> search(String keyword);

    BookResponseDto getById(Long id);

    BookResponseDto update(Long  id, BookRequestDto dto);

    void delete(Long id);


}
