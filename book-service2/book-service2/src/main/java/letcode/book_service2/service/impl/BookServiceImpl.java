package letcode.book_service2.service.impl;

import letcode.book_service2.dto.BookRequestDto;
import letcode.book_service2.dto.BookResponseDto;
import letcode.book_service2.entity.Author;
import letcode.book_service2.entity.Book;
import letcode.book_service2.entity.Category;
import letcode.book_service2.repository.AuthorRepo;
import letcode.book_service2.repository.BookRepo;
import letcode.book_service2.repository.CategoryRepo;
import letcode.book_service2.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "books")
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private  final AuthorRepo authorRepo;
    private  final CategoryRepo categoryRepo;

    @Override
    @CachePut(key = "#result.id")
    public BookResponseDto create(BookRequestDto dto) {

        Author author = authorRepo.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(author);
        book.setCategory(category);
        book.setAvailable(true);

        Book savedBook = bookRepo.save(book);

        return toDto(savedBook);
    }


    @Override
    @Cacheable(key = "'all'")
    public List<BookResponseDto> getAllBooks() {
        log.info("Fetching all books from DB");

        List<BookResponseDto> books = bookRepo.findAll()
                .stream()
                .map(this::toDto)
                .toList();

        log.info("Fetched {} books", books.size());
        return books;
    }

    @Override
    public List<BookResponseDto> search(String keyword) {
        List<Book> books =
                bookRepo
                        .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                                keyword, keyword
                        );

        return books.stream()
                .map(this::toDto)
                .toList();
    }


    @Override
    @Cacheable(key = "#id")
    public BookResponseDto getById(Long id) {
        log.info("Fetching book by id={}", id);

        Book book = bookRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Book not found with id={}", id);
                    return new RuntimeException("Book not found");
                });

        return toDto(book);
    }

    @Override
    @Cacheable(key="#id")
    public BookResponseDto getByAuthor(Long authorId){
        log.info("Fetching book by author={}", authorId);

        Book book=bookRepo.findById(authorId)
                .orElseThrow(()->{
                    log.error("Book not found with author={}", authorId);
                    return new RuntimeException("Book not found");
                });

        return toDto(book);
    }

    @Override
    @Cacheable(key="#id")
    public BookResponseDto getByCategory(Long categoryId){
        log.info("Fetching book by category={}", categoryId);

        Book book=bookRepo.findById(categoryId)
                .orElseThrow(()->{
                    log.error("Book not found with author={}", categoryId);
                    return new RuntimeException("Book not found");
                });

        return toDto(book);
    }


    @Override
    @CachePut(value="books",key="#id")
    public BookResponseDto update(Long id, BookRequestDto dto) {
        log.info("Updating book with id={}", id);

        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));


        book.setTitle(dto.getTitle());

        book.setAvailable(dto.isAvailable());

        Book updatedBook = bookRepo.save(book);

        return toDto(updatedBook);
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Long id) {
        log.info("Deleting book with id={}", id);

        bookRepo.deleteById(id);

        log.info("Book deleted with id={}", id);
    }



    private BookResponseDto toDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthor().getName())
                .categoryName(book.getCategory().getName())
                .available(book.isAvailable())
                .build();
    }
}