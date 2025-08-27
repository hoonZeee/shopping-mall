package com.example.demo.repository.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BaseProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String size;
    private String color;
    private String material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_product_id")
    private BaseProduct baseProduct;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "baseProductOption", cascade = CascadeType.ALL)
    private List<Product> products;

    public static BaseProductOption create(String size, String color, String material, BaseProduct baseProduct){
        return new BaseProductOption(
                null,
                size,
                color,
                material,
                baseProduct,
                null
        );
    }
}
