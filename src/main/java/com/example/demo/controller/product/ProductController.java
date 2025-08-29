package com.example.demo.controller.product;

import com.example.demo.controller.product.dto.*;
import com.example.demo.repository.user.entity.User;
import com.example.demo.service.application.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    @GetMapping
    public ResponseEntity<Page<ProductPageResponseDto>> getRegisteredProducts(
            @RequestParam(defaultValue = "0") int page, //0부터 시작
            @RequestParam(defaultValue = "10") int size //페이지당 디폴트 10개
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<ProductPageResponseDto> result = productService.getRegisteredProducts(pageable);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{productId}/status") //상태만 바꾸니까 patch
    public ResponseEntity<ProductAllResponseDto> changeStatus(
            @PathVariable Integer productId,
            @RequestBody @Valid ProductStatusUpdateRequestDto request
    ) {
        ProductAllResponseDto created = productService.reviewProduct(productId, request.getCurrentStatus());
        return ResponseEntity.ok(created);
    }

}
