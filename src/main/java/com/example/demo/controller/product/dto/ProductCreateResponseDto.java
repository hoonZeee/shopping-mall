package com.example.demo.controller.product.dto;

import com.example.demo.repository.product.entity.Product;
import com.example.demo.repository.product.entity.ProductImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductCreateResponseDto {

    private Integer id;
    private String name;
    private String baseProductName;
    private List<String> images;

    public static ProductCreateResponseDto from(Product entity){
        return new ProductCreateResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getBaseProductOption().getBaseProduct().getName(),
                entity.getProductImages().stream()
                        .map(ProductImage::getImageURL)
                        .toList()
        );
    }


}
