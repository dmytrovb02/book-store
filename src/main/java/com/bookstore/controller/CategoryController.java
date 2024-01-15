package com.bookstore.controller;

import com.bookstore.dto.book.BookDtoWithoutCategoryIds;
import com.bookstore.dto.book.BookResponseDto;
import com.bookstore.dto.category.CategoryRequestDto;
import com.bookstore.dto.category.CategoryResponseDto;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
@Tag(name = "Category", description = "Endpoints for managing categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping
    @Operation(summary = "Get all categories",
            description = "Get a list of all available categories")
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/{id}/books")
    @Operation(summary = "Get books by category id",
            description = "Get a list of books by category id")
    public BookResponseDto getBooksByCategoryId(
            @PathVariable(name = "id") Long id) {
        return bookService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get a specific category by its id",
            description = "Get a specific category by its id")
    public List<BookDtoWithoutCategoryIds> getCategoryById(@PathVariable Long id) {
        return bookService.findAllByCategoryId(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new category",
            description = "Create a new category")
    public CategoryResponseDto createCategory(@RequestBody @Valid CategoryRequestDto requestDto) {
        return categoryService.create(requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update some category",
            description = "Update some category")
    public CategoryResponseDto updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid CategoryRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete some category",
            description = "Delete some category")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
