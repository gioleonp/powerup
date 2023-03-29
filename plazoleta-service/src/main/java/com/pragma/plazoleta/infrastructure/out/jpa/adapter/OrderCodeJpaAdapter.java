package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.OrderCodeModel;
import com.pragma.plazoleta.domain.spi.persistence.IOrderCodePersistencePort;
import com.pragma.plazoleta.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderCodeEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IOrderCodeEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IOrderCodeRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderCodeJpaAdapter implements IOrderCodePersistencePort {

    private final IOrderCodeRepository orderCodeRepository;
    private final IOrderCodeEntityMapper orderCodeEntityMapper;

    @Override
    public OrderCodeModel saveOrderCode(OrderCodeModel orderCodeModel) {
        OrderCodeEntity orderCodeEntity =
                orderCodeRepository.save(orderCodeEntityMapper.toEntity(orderCodeModel));

        return orderCodeEntityMapper.toModel(orderCodeEntity);
    }

    @Override
    public OrderCodeModel getOrderCode(String orderCode) {
        Optional<OrderCodeEntity> orderCodeEntity = orderCodeRepository.findById(orderCode);
        if (orderCodeEntity.isEmpty()) {
            throw new NoDataFoundException();
        }
        return orderCodeEntityMapper.toModel(orderCodeEntity.get());
    }

    @Override
    public void deleteOrderCode(String orderCode) {

        orderCodeRepository.deleteById(orderCode);
    }

    @Override
    public OrderCodeModel getOrderCodeByIdOrder(Long idOrderCode) {
        Optional<OrderCodeEntity> orderCodeEntity =
                orderCodeRepository.findOrderCodeByIdOrder(idOrderCode);
        if (orderCodeEntity.isEmpty()) {
            throw new NoDataFoundException();
        }
        return orderCodeEntityMapper.toModel(orderCodeEntity.get());
    }
}
