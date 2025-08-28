package com.example.demo.repository.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imageUrl;

    private LocalDateTime uploadedAt;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productImage")
    @BatchSize(size = 100)
    @OrderBy("changedAt ASC")
    private List<Review> reviews;

    public static ProductImage create(String imageURL, Product product) {
        return new ProductImage(
                null,
                imageURL,
                LocalDateTime.now(),
                product,
                null
        );
    }

}
