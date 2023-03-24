package com.pragma.plazoleta.infrastructure.out.jpa.mapper;

import com.pragma.plazoleta.domain.model.OrderModel;import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderEntity;import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {

    OrderEntity toEntity(OrderModel orderModel);
    OrderModel toModel(OrderEntity orderEntity);
    List<OrderModel> toModelList(List<OrderEntity> order);
    List<OrderEntity> toEntityList(List<OrderModel> order);

}
