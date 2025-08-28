package com.example.demo.controller.product.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequestDto {

    private Integer baseProductId;

    @Size(max= 20, message = "상품명은 최대 20글자까지 가능합니다.")
    private String name;

    @Size(min = 1, max = 5, message = "사진은 최소 1장 이상, 최대 5장까지 가능합니다.")
    private List<String> imageUrls;



}
