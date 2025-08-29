package com.apple.shop;  // 파일 상단에 package 파일경로;(com 폴더 -> apple 폴더 -> shop 폴더) 라고 적어줘야 다른 파일에서도 여기 있던 코드를 사용가능하다.
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
//import java.util.ArrayList;
//import java.util.List;


// *** 웹서버 기능 개발 순서 *** (추후 필요시 기능 개발 순서 변동 가능)
// 1. @Entity Item.java 구현   // @Entity(독립체 or 테이블 의미) 붙이면 "Item" 이름으로 DBeaver - MySQL - 데이터베이스 'Shop'에 데이터 테이블 하나 생성해줌
// 2. interface ItemRepository.java 구현
// 3. @Controller ItemController.java 구현
// 4. html 파일 list.html 구현

@Controller   // @Controller 사용하면 springframework가 알아서 아래 코드(public class ItemController { ... }) 가져와서 ShopApplication 클래스 main 함수에 집어 넣어서 아래 웹서버 코드 실행
@RequiredArgsConstructor   // Lombok 문법 @RequiredArgsConstructor 사용
// 접근 제어자 "public" 없으면 같은 폴더(패키지) 안에서만 해당 클래스 사용가능
// 접근 제어자 "public" 붙이면 다른 폴더(패키지) 에서도 해당 클래스 사용 가능
public class ItemController {
    // 여기서 웹서버기능 제작가능

    // 2. DB입출력 원하는 클래스에서 repository 등록
    private final ItemRepository itemRepository;  // 변수 itemRepository에는 new ItemRepository() 들어있음 (DB입출력문법(함수)(repository.입출력문법함수();) 잔뜩 들어있음.)
    // 이 함수들 쓰고싶은 곳에 가서 변수로 등록하고 (@RequiredArgsConstructor 필요) (예) ItemController.java -> ItemController 클래스 -> private final ItemService itemService;
    // 해당 변수(itemService) 안에 new ItemService();를 넣어줌.
    private final ItemService itemService;

    // 클래스 상단에 Lombok 문법 @RequiredArgsConstructor 사용 안 하는 경우 아래처럼 ItemController 생성자 직접 구현 해야함.
    // @Autowired
    // public ItemController(ItemRepository itemRepository, ItemService itemService) {
    //     this.itemRepository = itemRepository;
    //     this.itemService = itemService;
    // }

    // Lombok 문법 @RequiredArgsConstructor 사용 하지 않는 경우 아래 코드 처럼 constructor(생성자 - ItemController) 사용 필수
    // constructor(생성자 - ItemController) 자동 생성 방법
    // 1) 소스파일 "ItemController" 열기 -> 해당 소스파일 마우스 우클릭 -> Generate... 클릭
    // 2) 팝업화면 "Generate" 출력 -> Constructor 클릭
    // 3) 팝업화면 "Choose Fields to Initialize by Constructor" 출력 -> 버튼 OK 클릭
    // 4) 아래처럼 constructor(생성자 - ItemController) 생성
    // 5) 아래 constructor(생성자 - ItemController) 바로 위에 @Autowired 추가
    // 아래 constructor(생성자 - ItemController)의 기능은 spring한테 new ItemRepository() 해서 object(객체) 생성 후 변수(private final ItemRepository itemRepository;)에 넣으라고 시키는 것이다.
    // @Autowired
    // public ItemController(ItemRepository itemRepository) {
    //   this.itemRepository = itemRepository;
    // }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    @GetMapping("/list")   // URL 작명시 명사가 좋음.
    // throws Exception - Exception을 뱉어주는 웹서버 API 함수 의미
    String list(Model model) throws Exception {  // Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용하기 위해 파라미터 Model model 추가

        // var result = itemRepository.findAll();   // DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 모든 데이터 꺼내주세요~ (List 타입(자료형) []으로 데이터 출력 중인 테이블 클래스 "Item" Object 형태로 데이터 가져옴)
        // List<Item> result = itemRepository.findAll();   // 데이터 출력 중인 테이블 클래스 "Item"으로 List 타입(자료형) 명확하게 해서 DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 모든 데이터 꺼내주세요~ (List 타입(자료형) []으로 데이터 출력 중인 테이블 클래스 "Item" Object 형태로 데이터 가져옴)
        List<Item> result = itemService.getItems();   // 아이템 리스트 가져오기

        model.addAttribute("items", result);   // html 파일에 보내고 싶은 웹서버에서보낸변수 이름 "items" , 값 result 메서드 addAttribute 사용해서 집어넣기
        // model.addAttribute("title", "비싼 바지");  // html 파일에 보내고 싶은 웹서버데이터 이름 "title", 값 "비싼 바지" 메서드 addAttribute 사용해서 집어넣기

        // throw new Exception();   // 강제로 에러 처리

        var a = new Item();
        System.out.println(a);  // 참고 - System.out.println(a); 처럼 .toString() 함수 생략하고 실행하더라도 Item @Entity 클래스 안에 속하는 .toString() 함수가 알아서 붙여서 해당 함수를 실행해줌.
        // System.out.println(a.title);
        System.out.println(a.getTitle());
        // System.out.println(Item.title);
        System.out.println(a.toString()); // Item.java 소스파일 -> 롬복(Lombok) 라이브러리 @ToString 사용하면 해당 Item @Entity 클래스 안에 속하는 .toString() 역할의 함수를 알아서 만들어준다.
        // (예) 위에 .toString() 함수 사용해서 Item @Entity 클래스 object 출력 예시
        // Item(id=null, title=null, price=null)

        return "list.html";   // 웹페이지(list.html) 사용자 웹브라우저 출력

        // TODO: 아래 주석친 테스트 코드 필요시 참고 (2025.08.13 minjae)
        // System.out.println(result);
        // System.out.println(result.get(0));  // DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 첫째 행(get(0)) 데이터 출력 (데이터 com.apple.shop.Item@6d17b11e 출력 - Item 클래스의 Object 라는 뜻이다.)
        // System.out.println(result.get(0).price);  // DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 첫째 행(get(0)) 가격(price) 데이터 출력
        // System.out.println(result.get(0).title);  // DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 첫째 행(get(0)) 제목(title) 데이터 출력
        // itemRepository.save();   // DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 데이터 저장해주세요~
        // JPA 사용해서 테이블에서 데이터 입출력하려면
        // 아래처럼 항상 3-step 이 필요하다.
        // 1. repository 만들기
        // 2. DB입출력 원하는 클래스에서 repository 등록
        // 3. DB입출력문법(함수)(repository.입출력문법함수();) 가져다가 쓰기

        // var a = new ArrayList<>();
        // ArrayList<Object> a = new ArrayList<>();  // 문자와 숫자 등 여러종류 타입(자료형)을 동시에 넣기 (또는 List<Object> b = new ArrayList<>();)
        // ArrayList<String> a = new ArrayList<>();  // 문자열 타입(자료형)
        // ArrayList<Integer> a = new ArrayList<>();    // 정수형 타입(자료형)
        // a.add(30);
        // a.add(40);
        // System.out.println(a);
        // System.out.println(a.get(0));
        // System.out.println(a.get(1));
    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    @GetMapping("/write")   // URL 작명시 명사가 좋음.
    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    String write() {
        // throw new Exception();   // 강제로 에러 처리
        return "write.html";   // 웹페이지(write.html) 사용자 웹브라우저 출력
    }

    // 상품추가 기능?
    // 1. 상품 이름, 가격 작성할 수 있는 웹페이지(write.html)와 폼 만들기
    // 2. 전송버튼 누르면 데이터 웹서버 전송 -> DB에 데이터 저장

    // 유저가 보낸 데이터를 웹서버 API 함수 writePost에서 출력하려면
    // 웹서버 API 함수 소괄호 안에 파라미터를 잘 적으면 되는데
    // 웹페이지(write.html)에 <input>보시면 title, price 라는 이름으로 인풋 데이터들을 보내고 있지 않습니까 (<input name="title"> <input name="price">)
    // 그걸 그대로 웹서버 API 함수 writePost 소괄호 안의 파라미터란에 적어주면 유저가 보낸 title, price를 파라미터 변수에 담아준다. (String writePost(String title, Integer price))
    // 그리고 왼쪽에 타입(String, Integer)을 적으면 그 타입으로 title, price 변수를 각각 변환해준다. (String writePost(String title, Integer price))

    // (참고1) @RequestParam 생략이 안된다면
    // 파일 - 설정 - java compiler 메뉴 검색해본 후에
    // additional command line parameter 입력란에 -parameters 입력잘되어있는지 확인해보자.
    // out 폴더도 삭제 후 다시 서버 띄워보자.

    // (참고2) <form> 말고 ajax의 body로 전송한 데이터는 @RequestBody라는걸 써야 출력해볼 수 있는데 나중에 알아보자.

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // CASE 1-1: 데이터 2가지만 웹서버로 들어오는 경우 (@RequestParam(name="title") String title, @RequestParam(name="price") Integer price))
    @PostMapping("/add")
        // String writePost(String title, String price) {
    String writePost(String title, Integer price) throws Exception {  // throws Exception - Exception을 뱉어주는 웹서버 API 함수 의미
        itemService.saveItem(title, price);  // 원하는 곳에서 변수.함수() 쓰면 된다. (예) ItemController.java -> ItemController 클래스 -> 웹서버 API 함수 writePost -> itemService.saveItem(title, price);
        return "redirect:/list";   // redirect:/list 이러면 특정 웹페이지(/list)로 유저를 강제 이동시킬 수 있다. (ajax로 요청하는 경우 이동불가능)

        // 근데 new ItemService().saveItem() 해서 사용하면 뭔가 비효율적인 것 같지 않습니까
        // 왜냐면 /add로 요청이 들어올 때 마다 매번 new 키워드로 object를 뽑아야하니까요.
        // 그게 싫으면 그냥 object를 한 번 뽑고 그걸 변수에 저장해뒀다가 계속 재사용하는 식으로 코드짜도 된다. (싱글톤 패턴 방식)
        // 근데 그걸 여러분이 직접 (싱글톤 패턴 방식) 코드로 구현해도 되겠지만 스프링에게 시킬 수도 있다.
        // new ItemService().saveItem(title, price);  // 두 클래스 간 (ItemController, ItemService) 커플링(연결고리) 발생하는 코드는 비효율적이다.
        // 3-step이 필요한데

        // 1. 클래스에 @Service 붙여놓고
        // (예)
        // @Service
        // @RequiredArgsConstructor - Lombok 문법
        // public class ItemService {
        //    private final ItemRepository itemRepository;

        //    public void saveItem(String title, Integer price){
        //        Item item = new Item();
        //        item.setTitle(title);
        //        item.setPrice(price);
        //        itemRepository.save(item);
        //    }
        // }

        // 2. 이 함수들 쓰고싶은 곳에 가서 변수로 등록하고 (@RequiredArgsConstructor 필요) (예) ItemController.java -> ItemController 클래스 -> private final ItemService itemService;
        // 3. 원하는 곳에서 변수.함수() 쓰면 된다. (예) ItemController.java -> ItemController 클래스 -> 웹서버 API 함수 writePost -> itemService.saveItem(title, price);

        // new Item()으로 생성한 변수에다가 여러가지 정보(데이터)를 채운 다음에
        // Item item = new Item();
        // item.setTitle(title);
        // item.setPrice(price);

        // 리포지토리.save() 함수 소괄호 안에 new Item()으로 생성한 변수를 넣으면 그 정보(데이터)들을 채워서 행을 하나 테이블에 데이터로 추가해준다.
        // itemRepository.save(item);

        // throw new Exception();   // 강제로 에러 처리

        // return "redirect:/list";   // redirect:/list 이러면 특정 웹페이지(/list)로 유저를 강제 이동시킬 수 있다. (ajax로 요청하는 경우 이동불가능)

        // TODO: 아래 주석친 코드 필요시 참고 (2025.08.20 minjae)
        // item.title = title;  // @Entity 클래스 Item 멤버변수 title 접근제한자 private으로 해놔서 .title 사용 불가
        // item.price = 123;    // @Entity 클래스 Item 멤버변수 price 접근제한자 private으로 해놔서 .price 사용 불가

        // item.title = "a";
        // item.price = 123;
    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // URL 파라미터 문법 사용해서 비슷한 URL(/detail/어쩌구)가진 웹서버 API가 여러개 작성할 필요 없이 하나의 웹서버 API만 작성하면 된다.
    // @PathVariable 사용 예시 1
    // try - catch 사용 예시 1
    @GetMapping("/detail/{id}")
//    ResponseEntity<String> detail(@PathVariable Long id, Model model) {
    String detail(@PathVariable Long id, Model model) {
      try {
        // Optional<Item> result = itemRepository.findById(id);
        Optional<Item> result = itemService.getItemById(id);
        // 그래서 아래 처럼 쓰면(if (result.isPresent())) 확실하게 값이 들어있을 경우에만 .get() 해서 데이터를 안전하게 사용할 수 있다. (result.get())
        if (result.isPresent()) {
          model.addAttribute("data", result.get());
          return "detail.html";
        } else {
          return "redirect:/list";
        }
      } catch(Exception e) {
          System.out.println(e.getMessage());  // 에러 원인(이유) 콘솔창 출력 (나중에 웹서버 배포 후 에러내역 기록해두려면 logging 라이브러리 추천함.)
          return "redirect:/list";   // try - catch 구문에서 ajax로 웹서버와 통신하면 redirect 사용불가
          // return ResponseEntity.status(400).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
          // 웹페이지나 데이터 못찾는 경우 404 NOT FOUND가 적절함.
          // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
      }
    }

    // 같은 컨트롤러 클래스 (ItemController.java) 안에 속한 모든 웹서버 API 함수들에서 에러 발생시
    // 대신 @ExceptionHandler(Exception.class) 붙인 handler() 함수 안의 코드 실행해줌.
    // 단점 - 컨트롤러 클래스 (예) ItemController.java 가 100만개 있으면 직접 그 파일마다 이 코드(@ExceptionHandler(Exception.class)) 넣는게 귀찮아지겠군요.
//    @ExceptionHandler(Exception.class)
//    public void handler() {
//      return ResponseEntity.status().body();
//    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // URL 파라미터 문법 사용해서 비슷한 URL(/detail/어쩌구)가진 웹서버 API가 여러개 작성할 필요 없이 하나의 웹서버 API만 작성하면 된다.
    // @PathVariable 사용 예시 2
    // try - catch 사용 예시 2
//    @GetMapping("/detail2/{id}")
//    ResponseEntity<String> detail2(@PathVariable Long id, Model model) {
//    // String detail(@PathVariable Long id, Model model) {
//        try {
//            Optional<Item> result = itemRepository.findById(id);
//            // 그래서 아래 처럼 쓰면(if (result.isPresent())) 확실하게 값이 들어있을 경우에만 .get() 해서 데이터를 안전하게 사용할 수 있다. (result.get())
//            if (result.isPresent()) {
//                model.addAttribute("data", result.get());
//                // return "detail.html";
//            } else {
//                // return "redirect:/list";
//            }
//        } catch(Exception e) {
//            System.out.println(e.getMessage());  // 에러 원인(이유) 콘솔창 출력 (나중에 웹서버 배포 후 에러내역 기록해두려면 logging 라이브러리 추천함.)
//            // return "redirect:/list";   // try - catch 구문에서 ajax로 웹서버와 통신하면 redirect 사용불가
//            // return ResponseEntity.status(400).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
//            // 웹페이지나 데이터 못찾는 경우 404 NOT FOUND가 적절함.
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
//        }
//    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // URL 파라미터 문법 사용해서 비슷한 URL(/detail/어쩌구)가진 웹서버 API가 여러개 작성할 필요 없이 하나의 웹서버 API만 작성하면 된다.
    // @PathVariable 사용 예시 3
    // try - catch 사용 예시 3
    // ResponseEntity<String> 사용 예시 3
//    @GetMapping("/test/{id}")
//    ResponseEntity<String> test(@PathVariable Long id, Model model) {
//        try {
//            throw new Exception("이런저런에러임");  // 에러 강제 발생
//            throw new Exception();  // 에러 강제 발생
//        } catch(Exception e) {
//            System.out.println(e.getMessage());  // 에러 원인(이유) 콘솔창 출력 (나중에 웹서버 배포 후 에러내역 기록해두려면 logging 라이브러리 추천함.)
//            return "redirect:/list";  // try - catch 구문에서 ajax로 웹서버와 통신하면 redirect 사용불가
//            return ResponseEntity.status(400).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
//            // 웹페이지나 데이터 못찾는 경우 404 NOT FOUND가 적절함.
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
//        }
//    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // URL 파라미터 문법 사용해서 비슷한 URL(/detail/어쩌구)가진 웹서버 API가 여러개 작성할 필요 없이 하나의 웹서버 API만 작성하면 된다.
    // @PathVariable 사용 예시 4
    // try - catch 사용 예시 4
    // ResponseEntity<String> 사용 예시 4
    @GetMapping("/test2/{id}")
    ResponseEntity<String> test2(@PathVariable Long id, Model model) throws Exception {  // 함수 test 뒤에 throws Exception 추가시 해당 함수는 try-catch 없이도 예외(에러)처리 가능
//        throw new Exception("이런저런에러임");  // 에러 강제 발생
        throw new Exception();  // 에러 강제 발생
//        try {
//
//        } catch(Exception e) {
//            System.out.println(e.getMessage());  // 에러 원인(이유) 콘솔창 출력 (나중에 웹서버 배포 후 에러내역 기록해두려면 logging 라이브러리 추천함.)
//            // return "redirect:/list";   // try - catch 구문에서 ajax로 웹서버와 통신하면 redirect 사용불가
//            return ResponseEntity.status(400).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
//            // 웹페이지나 데이터 못찾는 경우 404 NOT FOUND가 적절함.
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("니잘못임");   // try - catch 구문에서 ajax로 웹서버와 통신하면 ResponseEntity 사용 가능
//        }
    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // URL 파라미터 문법 사용해서 비슷한 URL(/detail/어쩌구)가진 웹서버 API가 여러개 작성할 필요 없이 하나의 웹서버 API만 작성하면 된다.
    // @PathVariable 사용 예시 2
//    @GetMapping("/detail/{id}")
//    // String detail() {
//    // (힌트) 유저가 URL 파라미터에 마구 입력한 값(id)을 서버에서 쉽게 알 수 있는데 @PathVariable 이라는걸 찾아보면 되겠다. (예) @PathVariable Long id
//    // TODO: 아래와 같은 오류 메시지 출력되어 @PathVariable Long id를 detail 웹서버 API 메서드 파라미터에 추가 완료 (2025.08.21 minjae)
//    // 오류 메시지 - Cannot resolve symbol 'id'
//    // 참고 URL - https://claude.ai/chat/577f41cb-1efb-45f6-ad06-52fb55c9b243
//    // Thymeleaf 템플릿 엔진 설치한 경우 에러 페이지(error.html) 만들어두면 유저가 URL 파라미터에 "http://localhost:8080/detail/abc" 입력 후 엔터키 -> "abc" 문자열이 Long 타입(자료형)으로 변환이 안되서 오류 발생 -> 에러 페이지(error.html) 웹브라우저 화면에 자동 출력
//    String detail(@PathVariable Long id, Model model) throws Exception {   // throws Exception - Exception을 뱉어주는 웹서버 API 함수 의미
//        // var result = itemRepository.findById(1L);
//        // Optional<Item> result = itemRepository.findById(1L);
//        Optional<Item> result = itemRepository.findById(id);   // 컬럼 id에 할당된 값(메서드 파라미터 변수 id)과 동일한 행(Raw)을 테이블에서 가져오기
//
//        // throw new Exception();   // 강제로 에러 처리
//
//        // Optional타입(자료형)result변수.isPresent() 라고 쓰면 result변수 안에 할당된 값이 뭐가 들어있으면 true를 그 자리에 남겨준다.
//        // 그래서 아래 처럼 쓰면(if (result.isPresent())) 확실하게 값이 들어있을 경우에만 .get() 해서 데이터를 안전하게 사용할 수 있다. (result.get())
//        if (result.isPresent()){
//            // Optional 자료(데이터)는 .get() 붙여야 안에 들어있는 자료(데이터)가 나온다. (result.get())
//            // 물론 result 변수에 값이 비어있을 수 있기 때문에(변수에 할당된 값이 null인 경우) 그냥 .get() 하고 그러면 웹서버가 에러나고 동작이 멈출 수 있다.
//            // 그래서 "만약에 result 변수 안에 할당된 값이 뭐가 있는 경우에만 .get() 해서 사용해라" 이렇게 쓰는게 안전하고 좋다.
//            System.out.println(result.get());   // ShopApplication 콘솔창(Console) 출력 결과 - (예) Item(id=1, title=셔츠, price=7000)
//            model.addAttribute("data", result.get());   // html 파일에 보내고 싶은 웹서버에서보낸변수 이름 "data" , 값 result.get() 메서드 addAttribute 사용해서 집어넣기
//            return "detail.html";
//        } else {   //  result변수 안에 할당된 값이 들어있지 않고 빈값인 경우 (null)
//            return "redirect:/list";   // redirect:/list 이러면 특정 웹페이지(/list)로 유저를 강제 이동시킬 수 있다. (ajax로 요청하는 경우 이동불가능)
//        }
//    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // URL 파라미터 문법 사용해서 비슷한 URL(/detail/어쩌구)가진 웹서버 API가 여러개 작성할 필요 없이 하나의 웹서버 API만 작성하면 된다.
    // 참고 사항 - URL 파라미터는 1개 이상 여러개도 사용 가능
    // URL 파라미터 문법 예시 1 - URL 파라미터 1 개만 사용 {id}
//    @GetMapping("/detail/{id}")
//    String detail(@PathVariable Long id, Model model) {
//      return "";
//    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // URL 파라미터 문법 사용해서 비슷한 URL(/detail/어쩌구)가진 웹서버 API가 여러개 작성할 필요 없이 하나의 웹서버 API만 작성하면 된다.
    // 참고 사항 - URL 파라미터는 1개 이상 여러개도 사용 가능
    // URL 파라미터 문법 예시 2 - URL 파라미터 2 개만 사용 {id} {id2}
//    @GetMapping("/detail/{id}/{id2}")
//    // Thymeleaf 템플릿 엔진 설치한 경우 에러 페이지(error.html) 만들어두면 유저가 URL 파라미터에 "http://localhost:8080/detail/abc" 입력 후 엔터키 -> "abc" 문자열이 Long 타입(자료형)으로 변환이 안되서 오류 발생 -> 에러 페이지(error.html) 웹브라우저 화면에 자동 출력
//    String detail(@PathVariable Long id, @PathVariable Long id2, Model model) {
//        return "";
//    }

    // TODO: 아래 주석친 코드 필요시 참고 (2025.08.20 minjae)
    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // CASE 1-2: 데이터 2가지만 웹서버로 들어오는 경우 (@RequestParam(name="title") String title, @RequestParam(name="price") Integer price))

//    @PostMapping("/add")   // URL 작명시 명사가 좋음.
//    // String writePost(@RequestParam Map<String, Object> formData) {
//    // String writePost(@RequestParam String title, @RequestParam Integer price) {
//    // String addPost(@RequestParam String title, @RequestParam Integer price) {
//    String writePost(@RequestParam(name="title") String title,
//                     @RequestParam(name="price") Integer price) {
//
//        Item newItem = new Item();
//        newItem.setTitle(title);
//        newItem.setPrice(price);
//        itemRepository.save(newItem);   // Item 테이블에 행(raw)으로 데이터 저장
//
//        // System.out.println(title);
//        // System.out.println(price);
//        // throw new Exception();   // 강제로 에러 처리
//
//        // var test = new Item();
//        // test.setTitle(title);
//        // test.setPrice(price);
//
//        return "redirect:/list";   // redirect:/list 이러면 특정 웹페이지(/list)로 유저를 강제 이동시킬 수 있다. (ajax로 요청하는 경우 이동불가능)
//    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // CASE 2-1: 데이터 여러개가 웹서버로 들어오는 경우
    // @ModelAttribute 사용해서 유저가 <input> 태그에 정보(데이터) 입력 후 웹서버 API 함수로 보낸 데이터를 @Entity 클래스 객체(object)로 쉽게 변환 처리
//    @PostMapping("/add")
//    String writePost(@ModelAttribute Item item) {
//        System.out.println(item);   // ShopApplication 콘솔창(Console) 출력 결과 - Item(id=null, title=aaaa, price=1234)
//        itemRepository.save(item);
//        return "redirect:/list";   // redirect:/list 이러면 특정 웹페이지(/list)로 유저를 강제 이동시킬 수 있다. (ajax로 요청하는 경우 이동불가능)
//    }


    // TODO: 아래 주석친 코드 필요시 참고 (2025.08.20 minjae)
    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // CASE 2-2: 데이터 여러개가 웹서버로 들어오는 경우
    // Map 자료형 형태로 유저가 보낸 모든 데이터 변환해줌 (@RequestParam Map<String, Object> formData)
    // Map 자료형은 여러 데이터 한 변수에 넣고 싶을 때 사용함.
//    @PostMapping("/add")   // URL 작명시 명사가 좋음.
//    // String writePost(@RequestParam Map<String, Object> formData) {
//    // String writePost(@RequestParam Map formData) {
//    String writePost(@RequestParam Map<String, Object> formData) {
//        // new Item()으로 생성한 변수에다가 여러가지 정보(데이터)를 채운 다음에
//        Item newItem = new Item();
//        newItem.setTitle(formData.get("title").toString());   // Map 자료형 변수 formData에서 key가 "title"인 value 값 문자열 변환(.toString()) 후 newItem.setTitle() 함수 호출
//
//        // TODO: 아래와 같은 오류 메시지 출력되어 형변환 방식 변경함. (2025.08.19 minjae)
//        //       (기존) 명시적 형변환 (Integer) -> (변경) Integer.valueOf(formData.get("price").toString())
//        // 오류 메시지 - There was an unexpected error (type=Internal Server Error, status=500).
//        //             class java.lang.String cannot be cast to class java.lang.Integer (java.lang.String and java.lang.Integer are in module java.base of loader 'bootstrap')
//        //             java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Integer (java.lang.String and java.lang.Integer are in module java.base of loader 'bootstrap')
//        //	           at com.apple.shop.ItemController.writePost(ItemController.java:147)
//        // 참고 URL - https://claude.ai/chat/8f335633-2258-45f2-b12f-ee216b920c58
//        // newItem.setPrice((Integer)formData.get("price"));   // Map 자료형 변수 formData에서 key가 "price"인 value 값 정수 변환(Integer) 후 newItem.setTitle() 함수 호출
//        newItem.setPrice(Integer.valueOf(formData.get("price").toString()));   // Map 자료형 변수 formData에서 key가 "price"인 value 값 문자열 변환(.toString()) 및 정수 변환(Integer.valueOf) 후 newItem.setPrice() 함수 호출
//        // throw new Exception();   // 강제로 에러 처리
//        // 리포지토리.save() 함수 소괄호 안에 new Item()으로 생성한 변수를 넣으면 그 정보(데이터)들을 채워서 행을 하나 테이블에 데이터로 추가해준다.
//        itemRepository.save(newItem);
//
//        return "redirect:/list";   // redirect:/list 이러면 특정 웹페이지(/list)로 유저를 강제 이동시킬 수 있다. (ajax로 요청하는 경우 이동불가능)
//
//        // TODO: 아래 주석친 코드 필요시 참고 (2025.08.19 minjae)
//        // var test = new HashMap<>();
//        // HashMap<String, Object> test = new HashMap<>();
//        // Map<String, Object> test = new HashMap<>();
//        // test.put("name", "kim");
//        // test.put("age", 20);
//        // System.out.println(test);
//        // System.out.println(test.get("name"));  // Map 자료형 변수 test에서 key가 "name"인 value 값 출력
//        // System.out.println(test.get("age"));  // Map 자료형 변수 test에서 key가 "age"인 value 값 출력
//
//        // System.out.println(formData);
//
//        // Map<String, Object> test = new HashMap<>();
//        // test.put("title", title);  자료이름: "title" 자료값: title
//        // test.put("price", price);  자료이름: "price" 자료값: price
//
//        // itemRepository.save(test);
//    }

    // 웹서버 API 작성 예시
    // @GetMapping("/list")   // URL 작명시 명사가 좋음.
    // String list(){
    //     return "list.html";
    // }
}