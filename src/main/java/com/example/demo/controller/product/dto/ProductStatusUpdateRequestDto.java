package com.example.demo.controller.product.dto;

import com.example.demo.repository.product.entity.vo.CurrentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductStatusUpdateRequestDto {

    @NotNull
    private CurrentStatus currentStatus;
    
}
