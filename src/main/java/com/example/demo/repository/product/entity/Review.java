package com.example.demo.repository.product.entity;

import com.example.demo.repository.product.entity.vo.CurrentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private CurrentStatus currentStatus;

    private LocalDateTime changedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_image_id")
    private ProductImage productImage;

    public static Review create(ProductImage productImage) {
        return new Review(
                null,
                CurrentStatus.REGISTERED,
                LocalDateTime.now(),
                productImage
        );
    }

    public static Review create(ProductImage productImage, CurrentStatus targetStatus) {
        return new Review(
                null,
                targetStatus,
                LocalDateTime.now(),
                productImage);
    }
}
