package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.response.CategoryResponseDto;
import com.pragma.plazoleta.domain.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {
    CategoryResponseDto toResponse(CategoryModel categoryModel);
    List<CategoryResponseDto> toResponseList(List<CategoryModel> categoryModelList);

}
