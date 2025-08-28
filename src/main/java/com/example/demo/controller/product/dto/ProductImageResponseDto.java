package com.example.demo.controller.product.dto;

import com.example.demo.repository.product.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductImageResponseDto {

    private Integer id;
    private String imageUrl;
    private List<ReviewResponseDto> reviews;

    public static ProductImageResponseDto from(ProductImage entity){
        List<ReviewResponseDto> reviewsDto = entity.getReviews().stream()
                .map(ReviewResponseDto::from)
                .toList();

        return new ProductImageResponseDto(
                entity.getId(),
                entity.getImageUrl(),
                reviewsDto
        );
    }

}
