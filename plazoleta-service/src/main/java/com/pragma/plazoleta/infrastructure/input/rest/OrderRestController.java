package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.OrderRequestDto;
import com.pragma.plazoleta.application.dto.response.OrderResponseDto;
import com.pragma.plazoleta.application.handler.IOrderHandler;
import com.pragma.plazoleta.domain.model.EOrderState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Operation(summary = "Save order")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Order saved", content = @Content)
            })
    @PostMapping("new")
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderRequestDto order) {
        orderHandler.createOrder(order, order.getPlatos());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all orders by restaurant and state")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "All orders found",
                        content = @Content),
                @ApiResponse(
                        responseCode = "404",
                        description = "No data found",
                        content = @Content)
            })
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

    @Operation(summary = "Assign order to employee")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Order is assigned to employee",
                        content = @Content)
            })
    @PatchMapping("/{id_pedido}")
    public ResponseEntity<Void> assignOrder(
            @PathVariable("id_pedido") Long idOrder, @RequestParam("employee") Long idEmployee) {
        orderHandler.assignOrder(idOrder, idEmployee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Order ready")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Order state was successfully changed to Ready",
                        content = @Content)
            })
    @PatchMapping("/ready/{id_pedido}")
    public ResponseEntity<Void> orderReady(
            @PathVariable("id_pedido") Long idOrder, @RequestParam("employee") Long idEmployee) {
        orderHandler.orderReady(idOrder, idEmployee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Deliver order")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Order was successfully delivered",
                        content = @Content)
            })
    @PatchMapping("/deliver/{id_pedido}")
    public ResponseEntity<Void> deliverOrder(
            @PathVariable("id_pedido") Long idOrder,
            @RequestParam("employee") Long idEmployee,
            @RequestParam("code") String code) {
        orderHandler.deliverOrder(idOrder, idEmployee, code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Cancel order")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Order was successfully cancelled",
                        content = @Content)
            })
    @PatchMapping("/cancel/{id_pedido}")
    public ResponseEntity<Void> cancelOrder(
            @PathVariable("id_pedido") Long idOrder, @RequestParam("client") Long idClient) {
        orderHandler.cancelOrder(idOrder, idClient);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
