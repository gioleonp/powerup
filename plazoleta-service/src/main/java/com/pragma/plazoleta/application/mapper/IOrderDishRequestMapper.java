package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.request.OrderDishRequestDto;import com.pragma.plazoleta.domain.model.OrderDishModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishRequestMapper {
    OrderDishModel toModel(OrderDishRequestDto orderDishRequestDto);
    List<OrderDishModel> toModelList(List<OrderDishRequestDto> orderDishRequestDto);
}
