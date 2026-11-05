package letcode.book_service2.controller;

import letcode.book_service2.dto.AuthorRequestDto;
import letcode.book_service2.dto.AuthorResponseDto;
import letcode.book_service2.dto.BookResponseDto;
import letcode.book_service2.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/create")
    public AuthorResponseDto create(@RequestBody AuthorRequestDto dto) {
        return authorService.create(dto);
    }

    @GetMapping("/getAllAuthors")
    public List<AuthorResponseDto> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorResponseDto getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @GetMapping("/search")
    public List<AuthorResponseDto> search(@RequestParam String keyword) {
        return authorService.search(keyword);
    }

    @GetMapping("/book/{Id}")
    public AuthorResponseDto getByBook(@PathVariable Long Id) {
        return authorService.getByBook(Id);
    }


}
