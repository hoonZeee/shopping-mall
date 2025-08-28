package com.example.demo.repository.product;

import com.example.demo.repository.product.entity.BaseProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseProductOptionRepository extends JpaRepository<BaseProductOption, Integer> {
}
