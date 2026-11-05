package letcode.book_service2.controller;

import letcode.book_service2.dto.CategoryRequestDto;
import letcode.book_service2.dto.CategoryResponseDto;
import letcode.book_service2.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public CategoryResponseDto create(@RequestBody CategoryRequestDto dto) {
        return categoryService.create(dto);
    }

    @GetMapping("/getAll")
    public List<CategoryResponseDto> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/search")
    public List<CategoryResponseDto> search(@RequestParam String keyword) {
        return  categoryService.search(keyword);
    }
}
