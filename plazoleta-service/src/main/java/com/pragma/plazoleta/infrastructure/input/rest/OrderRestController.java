package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.OrderRequestDto;
import com.pragma.plazoleta.application.handler.IOrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;import javax.validation.Valid;

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
}
