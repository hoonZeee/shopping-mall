package com.example.demo.controller.product.dto;

import com.example.demo.repository.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProductPageResponseDto {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;

    public static ProductPageResponseDto from(Product entity) {
        return new ProductPageResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt()
        );
    }
}
