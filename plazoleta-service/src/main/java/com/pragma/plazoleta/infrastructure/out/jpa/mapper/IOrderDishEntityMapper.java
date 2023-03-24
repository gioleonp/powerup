package com.pragma.plazoleta.infrastructure.out.jpa.mapper;

import com.pragma.plazoleta.domain.model.OrderDishModel;import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderDishEntity;import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishEntityMapper {

    OrderDishEntity toEntity(OrderDishModel orderDishModel);
    OrderDishModel toModel(OrderDishEntity orderDishEntity);

    List<OrderDishEntity> toEntityList(List<OrderDishModel> orderDishModelList);
    List<OrderDishModel> toModelList(List<OrderDishEntity> orderDishEntityList);


}
