package com.pragma.plazoleta.infrastructure.out.jpa.mapper;

import com.pragma.plazoleta.domain.model.OrderCodeModel;import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderCodeEntity;import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderCodeEntityMapper {

    OrderCodeEntity toEntity(OrderCodeModel order);
    OrderCodeModel toModel(OrderCodeEntity order);

}
