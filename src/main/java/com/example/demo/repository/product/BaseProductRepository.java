package com.example.demo.repository.product;

import com.example.demo.repository.product.entity.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseProductRepository extends JpaRepository<BaseProduct, Integer> {


}
