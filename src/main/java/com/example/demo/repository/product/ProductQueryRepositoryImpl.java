package com.example.demo.repository.product;

import com.example.demo.repository.product.entity.Product;
import com.example.demo.repository.product.entity.QProduct;
import com.example.demo.repository.product.entity.QProductImage;
import com.example.demo.repository.product.entity.QReview;
import com.example.demo.repository.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findAllWithImagesAndReviewsByUser(User user) {

        QProduct p = QProduct.product;
        QProductImage pi = QProductImage.productImage;
        QReview r = QReview.review;

        return jpaQueryFactory
                .selectFrom(p).distinct()
                .leftJoin(p.productImages, pi).fetchJoin()
                //.leftJoin(pi.reviews, r).fetchJoin() //카사디안 곱 MultipleBag 발생
                .orderBy(p.createdAt.desc()) //일단 상품 자체는 최신순으로, 승인 순서는 entity 에서 asc
                .fetch();

    }
}
