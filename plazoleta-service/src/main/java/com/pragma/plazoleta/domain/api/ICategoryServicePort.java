package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.application.dto.request.CategoryRequestDto;
import com.pragma.plazoleta.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(CategoryModel categoryModel);

    List<CategoryModel> getAllCategories();

}
