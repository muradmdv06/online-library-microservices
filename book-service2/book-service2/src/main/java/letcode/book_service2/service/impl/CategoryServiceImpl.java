package letcode.book_service2.service.impl;

import letcode.book_service2.dto.AuthorResponseDto;
import letcode.book_service2.dto.CategoryRequestDto;
import letcode.book_service2.dto.CategoryResponseDto;
import letcode.book_service2.entity.Author;
import letcode.book_service2.entity.Category;
import letcode.book_service2.repository.CategoryRepo;
import letcode.book_service2.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public CategoryResponseDto create(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());

        Category saved = categoryRepo.save(category);

        return CategoryResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .build();
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        return categoryRepo.findAll()
                .stream()
                .map(c -> CategoryResponseDto.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .build())
                .toList();
    }

    @Override
    public List<CategoryResponseDto> search(String keyword) {
        List<Category> categories =
                categoryRepo
                        .findByName(
                                keyword
                        );

        return categories.stream()
                .map(this::toDto)
                .toList();
    }

    private CategoryResponseDto toDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
