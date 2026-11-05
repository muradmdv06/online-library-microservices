package letcode.book_service2.service;

import letcode.book_service2.dto.CategoryRequestDto;
import letcode.book_service2.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto create(CategoryRequestDto dto);

    List<CategoryResponseDto> getAll();

    List<CategoryResponseDto> search(String keyword);
}
