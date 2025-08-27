package com.example.demo.repository.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    private Double price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "baseProduct", cascade = CascadeType.ALL)
    private List<BaseProductOption> baseProductOptions;


    public static BaseProduct create(String name, Double price){
        return new BaseProduct(
                null,
                name,
                price,
                null
        );
    }
}
