package com.pragma.plazoleta.infrastructure.configuration;

import com.pragma.plazoleta.domain.api.ICategoryServicePort;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IEmployeeServicePort;
import com.pragma.plazoleta.domain.api.IOrderCodeServicePort;
import com.pragma.plazoleta.domain.api.IOrderDishServicePort;
import com.pragma.plazoleta.domain.api.IOrderServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.spi.persistence.ICategoryPersistencePort;
import com.pragma.plazoleta.domain.spi.persistence.IDishPersistencePort;
import com.pragma.plazoleta.domain.spi.persistence.IEmployeePersistencePort;
import com.pragma.plazoleta.domain.spi.persistence.IOrderCodePersistencePort;
import com.pragma.plazoleta.domain.spi.persistence.IOrderDishPersistencePort;
import com.pragma.plazoleta.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.plazoleta.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.ITwilioServiceCommunicationPort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import com.pragma.plazoleta.domain.usecase.CategoryUseCase;
import com.pragma.plazoleta.domain.usecase.DishUseCase;
import com.pragma.plazoleta.domain.usecase.EmployeeUseCase;
import com.pragma.plazoleta.domain.usecase.OrderCodeUseCase;
import com.pragma.plazoleta.domain.usecase.OrderDishUseCase;
import com.pragma.plazoleta.domain.usecase.OrderUseCase;
import com.pragma.plazoleta.domain.usecase.RestaurantUseCase;
import com.pragma.plazoleta.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.pragma.plazoleta.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.pragma.plazoleta.infrastructure.out.jpa.adapter.EmployeeJpaAdapter;
import com.pragma.plazoleta.infrastructure.out.jpa.adapter.OrderCodeJpaAdapter;
import com.pragma.plazoleta.infrastructure.out.jpa.adapter.OrderDishJpaAdapter;
import com.pragma.plazoleta.infrastructure.out.jpa.adapter.OrderJpaAdapter;
import com.pragma.plazoleta.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IEmployeeEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IOrderCodeEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.ICategoryRepository;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IEmployeeRepository;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IOrderCodeRepository;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IOrderDishRepository;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IOrderRepository;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    private final IUserServiceCommunicationPort userServiceCommunicationPort;
    private final ITwilioServiceCommunicationPort twilioServiceCommunicationPort;

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    private final IEmployeeRepository employeeRepository;
    private final IEmployeeEntityMapper employeeEntityMapper;

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;

    private final IOrderCodeRepository orderCodeRepository;
    private final IOrderCodeEntityMapper orderCodeEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), userServiceCommunicationPort);
    }

    public IDishPersistencePort dishPersistencePort() {
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort(), restaurantServicePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IEmployeePersistencePort employeePersistencePort() {
        return new EmployeeJpaAdapter(employeeRepository, employeeEntityMapper);
    }

    @Bean
    public IEmployeeServicePort employeeServicePort() {
        return new EmployeeUseCase(
                employeePersistencePort(), userServiceCommunicationPort, restaurantServicePort());
    }


    @Bean
    public IOrderCodePersistencePort orderCodePersistencePort() {
        return new OrderCodeJpaAdapter(orderCodeRepository, orderCodeEntityMapper);
    }

    @Bean
    public IOrderCodeServicePort orderCodeServicePort() {
        return new OrderCodeUseCase(orderCodePersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }
    @Bean
    public IOrderDishPersistencePort orderDishPersistencePort() {
        return new OrderDishJpaAdapter(orderDishRepository, orderDishEntityMapper);
    }

    @Bean
    public IOrderDishServicePort orderDishServicePort() {
        return new OrderDishUseCase(
                orderDishPersistencePort(), dishServicePort(), orderPersistencePort());
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(
                orderPersistencePort(),
                orderDishServicePort(),
                userServiceCommunicationPort,
                employeeServicePort(),
                twilioServiceCommunicationPort,
                orderCodeServicePort());
    }
}
