package com.example.demo.controller.product;

import com.example.demo.controller.product.dto.ProductCreateRequestDto;
import com.example.demo.controller.product.dto.ProductCreateResponseDto;
import com.example.demo.repository.user.entity.User;
import com.example.demo.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductCreateResponseDto> createProduct(
            @RequestBody @Valid ProductCreateRequestDto request,
            @AuthenticationPrincipal User user
            ){
        ProductCreateResponseDto responseDto = productService.createProduct(request, user);
        return ResponseEntity.ok(responseDto);
    }


}
