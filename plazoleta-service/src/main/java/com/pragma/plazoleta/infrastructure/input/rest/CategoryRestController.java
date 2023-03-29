package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.CategoryRequestDto;
import com.pragma.plazoleta.application.dto.response.CategoryResponseDto;
import com.pragma.plazoleta.application.handler.ICategoryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Get all categories")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "All categories found",
                        content = @Content),
                @ApiResponse(
                        responseCode = "404",
                        description = "No data found",
                        content = @Content)
            })
    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryHandler.getAllCategories());
    }

    @Operation(summary = "Save category")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Category saved",
                        content = @Content)
            })
    @PostMapping()
    public ResponseEntity<Void> saveCategory(
            @Valid @RequestBody CategoryRequestDto categoryRequestDto) {

        categoryHandler.saveCategory(categoryRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
