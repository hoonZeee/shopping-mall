package com.example.demo.service.product;

import com.example.demo.controller.product.dto.ProductAllResponseDto;
import com.example.demo.controller.product.dto.ProductCreateRequestDto;
import com.example.demo.controller.product.dto.ProductCreateResponseDto;
import com.example.demo.controller.product.dto.ProductPageResponseDto;
import com.example.demo.repository.product.*;
import com.example.demo.repository.product.entity.BaseProduct;
import com.example.demo.repository.product.entity.BaseProductOption;
import com.example.demo.repository.product.entity.Product;
import com.example.demo.repository.product.entity.vo.CurrentStatus;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final BaseProductRepository baseProductRepository;
    private final BaseProductOptionRepository baseProductOptionRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {


        BaseProduct mugCup = baseProductRepository.save(BaseProduct.create("머그컵", 30000.0));
        BaseProduct cushion = baseProductRepository.save(BaseProduct.create("쿠션", 10000.0));

        baseProductOptionRepository.save(BaseProductOption.create("M", "블루", "도자기", mugCup));
        baseProductOptionRepository.save(BaseProductOption.create("S", "화이트", "가죽", cushion));


    }

    @Transactional
    public ProductCreateResponseDto createProduct(ProductCreateRequestDto request, User loginUser) {

        User user = userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        BaseProductOption baseProductOption = baseProductOptionRepository.findById(request.getBaseProductId())
                .orElseThrow(() -> new IllegalArgumentException("베이스 제품 옵션이 존재하지 않습니다."));

        Product product = Product.create(
                request.getName(),
                user,
                baseProductOption
                //ArrayList로 일단 초기화
        );

        //리스트 채우기 (양방향)
        request.getImageUrls().forEach(product::addImage);

        Product saved = productRepository.save(product);
        return ProductCreateResponseDto.from(saved);
    }

    @Transactional(readOnly = true)
    public List<ProductAllResponseDto> getMyProductsInform(User loginUser) {
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 유저"));

        return productRepository.findAllWithImagesAndReviewsByUser(user)
                .stream()
                .map(ProductAllResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<ProductPageResponseDto> getRegisteredProducts(Pageable pageable) {
        return productRepository.findByCurrentStatus(CurrentStatus.REGISTERED, pageable)
                .map(ProductPageResponseDto::from);
    }
}

