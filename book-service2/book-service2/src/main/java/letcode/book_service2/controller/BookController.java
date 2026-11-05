package letcode.book_service2.controller;

import letcode.book_service2.dto.BookRequestDto;
import letcode.book_service2.dto.BookResponseDto;
import letcode.book_service2.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public BookResponseDto create(@RequestBody BookRequestDto dto) {
        return bookService.create(dto);
    }

    @GetMapping("/getAllBooks")
    public List<BookResponseDto> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/author/{authorId}")
    public BookResponseDto getByAuthor(@PathVariable Long authorId) {
        return bookService.getByAuthor(authorId);
    }

    @GetMapping("/search")
    public List<BookResponseDto> search(@RequestParam String keyword) {
        return bookService.search(keyword);
    }

    @PutMapping("/{id}")
    public BookResponseDto update(@PathVariable Long id, @RequestBody BookRequestDto dto) {
        return bookService.update(id, dto);
    }

    @GetMapping("/{id}")
    public BookResponseDto getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/category/{categoryId}")
    public BookResponseDto getByCategory(@PathVariable Long categoryId) {
        return bookService.getByCategory(categoryId);
    }
}
