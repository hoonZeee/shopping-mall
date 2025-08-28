package com.example.demo.controller.product.dto;

import com.example.demo.repository.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductAllResponseDto {

    private Integer id;
    private String name;
    private String currentStatus;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    private List<ProductImageResponseDto> images;

    public static ProductAllResponseDto from(Product entity){

        List<ProductImageResponseDto> imagesDto = entity.getProductImages().stream()
                .map(ProductImageResponseDto::from)
                .toList();

        return new ProductAllResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getCurrentStatus().name(),
                entity.getCreatedAt(),
                imagesDto
        );
    }

}
