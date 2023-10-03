package com.example.HelpDesk.Service.Impl;

import com.example.HelpDesk.Dto.CategoryDto;
import com.example.HelpDesk.Model.Category;
import com.example.HelpDesk.Model.Response;
import com.example.HelpDesk.Repository.CategoryRepository;
import com.example.HelpDesk.Repository.TicketRepository;
import com.example.HelpDesk.Service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;



    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category newCategory = categoryRepository.save(category);

        CategoryDto categoryResponse = new CategoryDto();
        categoryResponse.setId(newCategory.getId());
        categoryResponse.setName(newCategory.getName());
        categoryResponse.setDescription(newCategory.getDescription());

        return categoryResponse;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> allCategories = categoryRepository.findAll();
        return allCategories.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Response<CategoryDto> finById(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            CategoryDto categoryDto = mapToDto(optionalCategory.get());
            return new Response<>("Success", "Category found", categoryDto);
        }
        return new Response<>("Error", "Category with id " + id + " not found", null);
    }

    @Override
    public Response<CategoryDto> updateCategory(CategoryDto categoryDto, int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(categoryDto.getName());
            existingCategory.setDescription(categoryDto.getDescription());

            // Sauvegardez la mise à jour dans le référentiel
            categoryRepository.save(existingCategory);

            return new Response<>("Success", "Category updated", mapToDto(existingCategory));
        }
        return new Response<>("Error", "Category with id " + id + " not found", null);
    }





    @Override
    public Response deleteCategory(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            // Supprimez la catégorie si elle existe dans le référentiel
            categoryRepository.delete(optionalCategory.get());
            return new Response("Success", "Category deleted", null);
        }
        return new Response("Error", "Category with id " + id + " does not exist", null);
    }

    private CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

}
