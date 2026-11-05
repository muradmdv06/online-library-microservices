package letcode.book_service2.service;

import letcode.book_service2.dto.AuthorRequestDto;
import letcode.book_service2.dto.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto create(AuthorRequestDto dto);

    List<AuthorResponseDto> getAll();

    AuthorResponseDto getById(Long id);

    List<AuthorResponseDto> search(String keyword);

    AuthorResponseDto getByBook(Long id);
}
