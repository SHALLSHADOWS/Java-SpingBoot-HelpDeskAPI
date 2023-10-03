package com.example.HelpDesk.Controller;

import com.example.HelpDesk.Dto.CategoryDto;
import com.example.HelpDesk.Model.Response;
import com.example.HelpDesk.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto categoryCreate = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryCreate, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public List<CategoryDto> findAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> findCategoryById(@PathVariable int id) {
        Response<CategoryDto> response = categoryService.finById(id);
        if ("Error".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable int id) {
        Response<CategoryDto> response = categoryService.updateCategory(categoryDto, id);
        if ("Error".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable int id) {
        Response response = categoryService.deleteCategory(id);
        if ("Error".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

