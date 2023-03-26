package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.OrderRequestDto;
import com.pragma.plazoleta.application.dto.response.OrderResponseDto;
import com.pragma.plazoleta.application.handler.IOrderHandler;
import com.pragma.plazoleta.domain.model.EOrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pedido")
public class OrderRestController {

    private final IOrderHandler orderHandler;

    @PostMapping("")
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderRequestDto order) {
        orderHandler.createOrder(order, order.getPlatos());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<OrderResponseDto>> findAllOrderByStateAndRestaurant(
            @RequestParam("estado") EOrderState state,
            @RequestParam("empleado") Long idEmployee,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return new ResponseEntity<>(
                orderHandler.findAllOrdersByStatusAndRestaurant(state, idEmployee, page, size),
                HttpStatus.OK);
    }
}
