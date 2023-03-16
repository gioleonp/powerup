package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryPersistencePort {

    CategoryModel saveCategory(CategoryModel categoryModel);

    List<CategoryModel> getAllCategories();
}
