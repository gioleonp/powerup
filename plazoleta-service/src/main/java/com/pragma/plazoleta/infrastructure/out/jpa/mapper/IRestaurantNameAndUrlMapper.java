package com.pragma.plazoleta.infrastructure.out.jpa.mapper;

import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.RestaurantNameAndUrlModel;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantNameAndUrlEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantNameAndUrlMapper {

    RestaurantNameAndUrlModel toRestaurantNameAndUrlModel(
            RestaurantNameAndUrlEntity restaurantNameAndUrlEntity);

    List<RestaurantNameAndUrlModel> toRestaurantModel(List<RestaurantNameAndUrlEntity> restaurants);
}
