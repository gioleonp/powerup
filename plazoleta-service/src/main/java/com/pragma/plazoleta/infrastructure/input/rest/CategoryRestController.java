package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.CategoryRequestDto;
import com.pragma.plazoleta.application.dto.response.CategoryResponseDto;
import com.pragma.plazoleta.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryRestController {

  private final ICategoryHandler categoryHandler;

  @GetMapping("")
  public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
    return ResponseEntity.ok(categoryHandler.getAllCategories());
  }

  @PostMapping()
  public ResponseEntity<Void> saveCategory(@RequestBody CategoryRequestDto categoryRequestDto) {

    categoryHandler.saveCategory(categoryRequestDto);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
