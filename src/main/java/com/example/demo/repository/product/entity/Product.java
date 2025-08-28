package com.example.demo.repository.product.entity;

import com.example.demo.repository.product.entity.vo.CurrentStatus;
import com.example.demo.repository.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CurrentStatus currentStatus;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_product_option_id")
    private BaseProductOption baseProductOption;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages = new ArrayList<>();

    public static Product create(String name, User user, BaseProductOption baseProductOption) {
        return new Product(
                null,
                name,
                CurrentStatus.REGISTERED,
                LocalDateTime.now(),
                user,
                baseProductOption,
                new ArrayList<>()
        );
    }

    public void addImage(String url) {
        ProductImage image = ProductImage.create(url, this); //String url 로 ProductImage 만들기
        productImages.add(image); //만든 ProductImage 추가하기
    }
}
