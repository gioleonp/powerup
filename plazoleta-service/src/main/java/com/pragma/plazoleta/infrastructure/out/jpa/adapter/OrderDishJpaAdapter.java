package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.OrderDishModel;
import com.pragma.plazoleta.domain.spi.persistence.IOrderDishPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.NoDataFoundException;import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderDishEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IOrderDishRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class OrderDishJpaAdapter implements IOrderDishPersistencePort {

    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;

    @Override
    public OrderDishModel createOrderDish(OrderDishModel orderDishModel) {

        OrderDishEntity orderDish = orderDishEntityMapper.toEntity(orderDishModel);

        return orderDishEntityMapper.toModel(orderDishRepository.save(orderDish));
    }

    @Override
    public List<OrderDishModel> saveAll(List<OrderDishModel> orderDishModels) {
        List<OrderDishEntity> orderDishEntities =
                orderDishEntityMapper.toEntityList(orderDishModels);

        return orderDishEntityMapper.toModelList(orderDishRepository.saveAll(orderDishEntities));
    }

    @Override
    public List<OrderDishModel> findAllByIdPedido(Long idPedido) {
        List<OrderDishEntity> orderDishEntities = orderDishRepository.findAllByIdPedido(idPedido);
        if (orderDishEntities.isEmpty()) {
            throw new NoDataFoundException();
        }
        return orderDishEntityMapper.toModelList(orderDishEntities);
    }
}
