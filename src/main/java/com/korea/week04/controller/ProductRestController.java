package com.korea.week04.controller;


import com.korea.week04.models.Product;
import com.korea.week04.models.ProductMypriceRequestDto;
import com.korea.week04.models.ProductRepository;
import com.korea.week04.models.ProductRequestDto;
import com.korea.week04.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor //final로 선언된 멤버 변수를 자동으로 생성
@RestController//json으로 데이터를 주고받음을 선언
public class ProductRestController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    //등록된 전체 상품목록 조회
    @GetMapping("/api/products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    // 신규 상품 등록   // 등록하는 api 추가
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = new Product(requestDto);
        productRepository.save(product);
        return product;
    }

    // 관심 상품 가격 등록
    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto){
        return productService.update(id, requestDto);
    }


    @DeleteMapping("/api/products/{id}")
    public Long deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return id;
    }
}