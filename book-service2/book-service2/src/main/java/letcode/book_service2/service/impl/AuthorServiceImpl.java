package letcode.book_service2.service.impl;

import letcode.book_service2.dto.AuthorRequestDto;
import letcode.book_service2.dto.AuthorResponseDto;
import letcode.book_service2.dto.BookResponseDto;
import letcode.book_service2.entity.Author;
import letcode.book_service2.entity.Book;
import letcode.book_service2.repository.AuthorRepo;
import letcode.book_service2.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepo authorRepo;

    @Override
    @CachePut(key = "#result.id")
    public AuthorResponseDto create(AuthorRequestDto dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setBiography(dto.getBiography());

        Author saved = authorRepo.save(author);

        return AuthorResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .biography(saved.getBiography())
                .build();
    }

    @Override
    @Cacheable(key = "'all'")
    public List<AuthorResponseDto> getAll() {
        return authorRepo.findAll()
                .stream()
                .map(a -> AuthorResponseDto.builder()
                        .id(a.getId())
                        .name(a.getName())
                        .biography(a.getBiography())
                        .build())
                .toList();
    }

    @Override
    @Cacheable(key = "#id")
    public AuthorResponseDto getById(Long id) {

        Author author=authorRepo.findById(id)
                .orElseThrow(()->{
                    return new RuntimeException("Author Not Found");
                });

        return toDto(author);
    }

    @Override
    public List<AuthorResponseDto> search(String keyword) {
        List<Author> authors =
                authorRepo
                        .findByName(
                                keyword
                        );

        return authors.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Cacheable(key="#id")
    public AuthorResponseDto getByBook(Long Id){
        log.info("Fetching author by book={}", Id);

        Author author=authorRepo.findById(Id)
                .orElseThrow(()->{
                    log.error("Book not found with author={}", Id);
                    return new RuntimeException("Book not found");
                });

        return toDto(author);
    }


    private AuthorResponseDto toDto(Author author) {
        return AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .biography(author.getBiography())
                .build();
    }
}
