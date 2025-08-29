package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;


// *** 웹서버 기능 개발 순서 *** (추후 필요시 기능 개발 순서 변동 가능)
// 1. @Entity Product.java 구현   // @Entity(독립체 or 테이블 의미) 붙이면 "Product" 이름으로 DBeaver - MySQL - 데이터베이스 'Shop'에 데이터 테이블 하나 생성해줌
// 2. interface ProductRepository.java 구현
// 3. @Controller ProductController.java 구현
// 4. html 파일 product_list.html 구현

@Controller   // @Controller 사용하면 springframework가 알아서 아래 코드(public class ProductController { ... }) 가져와서 ShopApplication 클래스 main 함수에 집어 넣어서 아래 웹서버 코드 실행
@RequiredArgsConstructor   // Lombok 문법 @RequiredArgsConstructor 사용
// 접근 제어자 "public" 없으면 같은 폴더(패키지) 안에서만 해당 클래스 사용가능
// 접근 제어자 "public" 붙이면 다른 폴더(패키지) 에서도 해당 클래스 사용 가능
public class ProductController {

    private final ProductRepository productRepository;   // 변수 productRepository에는 new ProductRepository() 들어있음 (DB입출력문법(함수)(repository.입출력문법함수();) 잔뜩 들어있음.)
    // 이 함수들 쓰고싶은 곳에 가서 변수로 등록하고 (@RequiredArgsConstructor 필요) (예) ProductController.java -> ProductController 클래스 -> private final ProductService productService;
    // 해당 변수(productService) 안에 new ProductService();를 넣어줌.
    private final ProductService productService;

    @GetMapping("/product_list")
    String product_list(Model model) {
      try {
          // var result = productRepository.findAll();   // DBeaver - MySQL - shop 데이터베이스 - product 데이터테이블에 저장된 모든 데이터 꺼내주세요~ (List 타입(자료형) []으로 데이터 출력 중인 테이블 클래스 "Product" Object 형태로 데이터 가져옴)
          // List<Product> result = productRepository.findAll();   // 데이터 출력 중인 테이블 클래스 "Product"으로 List 타입(자료형) 명확하게 해서 DBeaver - MySQL - shop 데이터베이스 - product 데이터테이블에 저장된 모든 데이터 꺼내주세요~ (List 타입(자료형) []으로 데이터 출력 중인 테이블 클래스 "Product" Object 형태로 데이터 가져옴)
          List<Product> result = productService.getItems();  // 제품 리스트 가져오기

          model.addAttribute("items", result);   // html 파일에 보내고 싶은 웹서버에서보낸변수 이름 "items" , 값 result 메서드 addAttribute 사용해서 집어넣기
          // model.addAttribute("name", "비싼 바지");  // html 파일에 보내고 싶은 웹서버데이터 이름 "name", 값 "비싼 바지" 메서드 addAttribute 사용해서 집어넣기

          return "product_list.html";   // 웹페이지(product_list.html) 사용자 웹브라우저 출력

      } catch(Exception e) {
          System.out.println(e.getMessage());  // 에러 원인(이유) 콘솔창 출력 (나중에 웹서버 배포 후 에러내역 기록해두려면 logging 라이브러리 추천함.)
          return "redirect:/product_list";   // try - catch 구문에서 ajax로 웹서버와 통신하면 redirect 사용불가
          // return ResponseEntity.status(400).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
          // 웹페이지나 데이터 못찾는 경우 404 NOT FOUND가 적절함.
          // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
      }
    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    @GetMapping("/product_write")   // URL 작명시 명사가 좋음.
    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    String product_write() {
        // throw new Exception();   // 강제로 에러 처리
        return "product_write.html";   // 웹페이지(product_write.html) 사용자 웹브라우저 출력
    }

    // 상품추가 기능?
    // 1. 상품 이름, 가격 작성할 수 있는 웹페이지(product_write.html)와 폼 만들기
    // 2. 전송버튼 누르면 데이터 웹서버 전송 -> DB에 데이터 저장

    // 유저가 보낸 데이터를 웹서버 API 함수 product_writePost에서 출력하려면
    // 웹서버 API 함수 소괄호 안에 파라미터를 잘 적으면 되는데
    // 웹페이지(product_write.html)에 <input>보시면 name, price 라는 이름으로 인풋 데이터들을 보내고 있지 않습니까 (<input name="name"> <input name="price">)
    // 그걸 그대로 웹서버 API 함수 product_writePost 소괄호 안의 파라미터란에 적어주면 유저가 보낸 name, price를 파라미터 변수에 담아준다. (String product_writePost(String name, Integer price))
    // 그리고 왼쪽에 타입(String, Integer)을 적으면 그 타입으로 name, price 변수를 각각 변환해준다. (String product_writePost(String title, Integer price))

    // (참고1) @RequestParam 생략이 안된다면
    // 파일 - 설정 - java compiler 메뉴 검색해본 후에
    // additional command line parameter 입력란에 -parameters 입력잘되어있는지 확인해보자.
    // out 폴더도 삭제 후 다시 서버 띄워보자.

    // (참고2) <form> 말고 ajax의 body로 전송한 데이터는 @RequestBody라는걸 써야 출력해볼 수 있는데 나중에 알아보자.

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // CASE 1-1: 데이터 2가지만 웹서버로 들어오는 경우 (@RequestParam(name="name") String name, @RequestParam(name="price") Integer price))
    @PostMapping("/product_add")
    String product_writePost(String name, Integer price) {
        try {
            productService.saveProduct(name, price);   // 원하는 곳에서 변수.함수() 쓰면 된다. (예) ProductController.java -> ProductController 클래스 -> 웹서버 API 함수 product_writePost -> productService.saveProduct(name, price);
            return "redirect:/product_list";   // redirect:/product_list 이러면 특정 웹페이지(/product_list)로 유저를 강제 이동시킬 수 있다. (ajax로 요청하는 경우 이동불가능)
            // new Product()으로 생성한 변수에다가 여러가지 정보(데이터)를 채운 다음에
            // Product product = new Product();
            // product.setName(name);
            // product.setPrice(price);

            // 리포지토리.save() 함수 소괄호 안에 new Product()으로 생성한 변수를 넣으면 그 정보(데이터)들을 채워서 행을 하나 테이블에 데이터로 추가해준다.
            // productRepository.save(product);

            // throw new Exception();   // 강제로 에러 처리

            // return "redirect:/product_list";   // redirect:/product_list 이러면 특정 웹페이지(/product_list)로 유저를 강제 이동시킬 수 있다. (ajax로 요청하는 경우 이동불가능)

        } catch(Exception e) {
            System.out.println(e.getMessage());  // 에러 원인(이유) 콘솔창 출력 (나중에 웹서버 배포 후 에러내역 기록해두려면 logging 라이브러리 추천함.)
            return "redirect:/product_list";   // try - catch 구문에서 ajax로 웹서버와 통신하면 redirect 사용불가
            // return ResponseEntity.status(400).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
            // 웹페이지나 데이터 못찾는 경우 404 NOT FOUND가 적절함.
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
        }
    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // URL 파라미터 문법 사용해서 비슷한 URL(/product_detail/어쩌구)가진 웹서버 API가 여러개 작성할 필요 없이 하나의 웹서버 API만 작성하면 된다.
    // @PathVariable 사용 예시 1
    // try - catch 사용 예시 1
    @GetMapping("/product_detail/{id}")
//    ResponseEntity<String> detail(@PathVariable Long id, Model model) {
    String product_detail(@PathVariable Long id, Model model) {
      try {
        // Optional<Item> result = productRepository.findById(id);
        Optional<Product> result = productService.getItemById(id);
        // 그래서 아래 처럼 쓰면(if (result.isPresent())) 확실하게 값이 들어있을 경우에만 .get() 해서 데이터를 안전하게 사용할 수 있다. (result.get())
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "product_detail.html";
        } else {
            return "redirect:/product_list";
        }
      } catch(Exception e) {
          System.out.println(e.getMessage());  // 에러 원인(이유) 콘솔창 출력 (나중에 웹서버 배포 후 에러내역 기록해두려면 logging 라이브러리 추천함.)
          return "redirect:/product_list";   // try - catch 구문에서 ajax로 웹서버와 통신하면 redirect 사용불가
          // return ResponseEntity.status(400).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
          // 웹페이지나 데이터 못찾는 경우 404 NOT FOUND가 적절함.
          // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
      }
    }

}
