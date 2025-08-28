package com.example.demo.repository.product;

import com.example.demo.repository.product.entity.Product;
import com.example.demo.repository.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductQueryRepository {
    List<Product> findAllWithImagesAndReviewsByUser(User user);
}
