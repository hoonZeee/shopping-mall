package com.example.demo.repository.product;

import com.example.demo.repository.product.entity.Product;
import com.example.demo.repository.product.entity.vo.CurrentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductQueryRepository {
    Page<Product> findByCurrentStatus(CurrentStatus currentStatus, Pageable pageable);

}
