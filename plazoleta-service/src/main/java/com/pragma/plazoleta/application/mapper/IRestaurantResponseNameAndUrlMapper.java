package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.request.RestaurantNameAndUrlDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseNameAndUrlDto;
import com.pragma.plazoleta.domain.model.RestaurantNameAndUrlModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseNameAndUrlMapper {

    RestaurantResponseNameAndUrlDto toRestaurantResponseNameAndUrl(
            RestaurantNameAndUrlModel restaurantNameAndUrlModel);

    List<RestaurantResponseNameAndUrlDto> toRestaurantResponseNameAndUrlList(
            List<RestaurantNameAndUrlModel> restaurantNameAndUrlModel);
}
