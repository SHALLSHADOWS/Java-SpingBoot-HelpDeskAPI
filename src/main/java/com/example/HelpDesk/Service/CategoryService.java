package com.example.HelpDesk.Service;

import com.example.HelpDesk.Dto.CategoryDto;
import com.example.HelpDesk.Dto.TicketDto;
import com.example.HelpDesk.Model.Response;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> findAll();

    Response<CategoryDto> finById(int id);

    Response<CategoryDto> updateCategory(CategoryDto categoryDto, int id);

    Response deleteCategory(int id);
}
