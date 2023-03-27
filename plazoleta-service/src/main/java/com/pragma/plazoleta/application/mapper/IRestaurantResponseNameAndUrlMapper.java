package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.response.RestaurantResponseNameAndUrlDto;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseNameAndUrlMapper {

    RestaurantResponseNameAndUrlDto toRestaurantResponseNameAndUrl(
             RestaurantModel restaurantModel);

    List<RestaurantResponseNameAndUrlDto> toRestaurantResponseNameAndUrlList(
            List<RestaurantModel> restaurantModelList);
}
