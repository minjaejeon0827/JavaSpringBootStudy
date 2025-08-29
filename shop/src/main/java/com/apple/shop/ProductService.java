package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 제품 저장
    public void saveProduct(String name, Integer price) {
        // new Product()으로 생성한 변수에다가 여러가지 정보(데이터)를 채운 다음에
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        // 리포지토리.save() 함수 소괄호 안에 new Product()으로 생성한 변수를 넣으면 그 정보(데이터)들을 채워서 행을 하나 테이블에 데이터로 추가해준다.
        productRepository.save(product);
    }

    // 제품 리스트 가져오기
    public List<Product> getItems() {
        return productRepository.findAll();
    }

    // 특정 id 값을 가진 제품 가져오기
    public Optional<Product> getItemById(Long id) {
        return productRepository.findById(id);
    }
}
