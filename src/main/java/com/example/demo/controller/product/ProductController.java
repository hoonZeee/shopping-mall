package com.example.demo.controller.product;

import com.example.demo.controller.product.dto.ProductAllResponseDto;
import com.example.demo.controller.product.dto.ProductCreateRequestDto;
import com.example.demo.controller.product.dto.ProductCreateResponseDto;
import com.example.demo.repository.user.entity.User;
import com.example.demo.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductCreateResponseDto> createProduct(
            @RequestBody @Valid ProductCreateRequestDto request,
            @AuthenticationPrincipal User user
    ) {
        ProductCreateResponseDto responseDto = productService.createProduct(request, user);
        return ResponseEntity.ok(responseDto);
    }


    @GetMapping("/me")
    public ResponseEntity<List<ProductAllResponseDto>> getMyProduct(@AuthenticationPrincipal User user) {
        List<ProductAllResponseDto> products = productService.getMyProductsInform(user);
        return ResponseEntity.ok(products);
    }


}
