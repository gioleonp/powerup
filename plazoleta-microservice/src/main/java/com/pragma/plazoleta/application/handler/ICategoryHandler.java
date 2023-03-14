package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.CategoryRequestDto;
import com.pragma.plazoleta.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoleta.application.dto.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryHandler {
    void saveCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> getAllCategories();
}
