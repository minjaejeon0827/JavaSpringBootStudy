package com.apple.shop;  // 파일 상단에 package 파일경로;(com 폴더 -> apple 폴더 -> shop 폴더) 라고 적어줘야 다른 파일에서도 여기 있던 코드를 사용가능하다.
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    String list(Model model) {  // Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용하기 위해 파라미터 Model model 추가

        // var result = itemRepository.findAll();   // DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 모든 데이터 꺼내주세요~ (List 타입(자료형) []으로 데이터 출력 중인 테이블 클래스 "Item" Object 형태로 데이터 가져옴)
        List<Item> result = itemRepository.findAll();   // 데이터 출력 중인 테이블 클래스 "Item"으로 List 타입(자료형) 명확하게 해서 DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 모든 데이터 꺼내주세요~ (List 타입(자료형) []으로 데이터 출력 중인 테이블 클래스 "Item" Object 형태로 데이터 가져옴)

        model.addAttribute("items", result);   // html 파일에 보내고 싶은 웹서버에서보낸변수 이름 "items" , 값 result 메서드 addAttribute 사용해서 집어넣기
        // model.addAttribute("name", "비싼 바지");  // html 파일에 보내고 싶은 웹서버데이터 이름 "name", 값 "비싼 바지" 메서드 addAttribute 사용해서 집어넣기

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
    String write() {  // Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용하기 위해 파라미터 Model model 추가
        return "write.html";   // 웹페이지(list.html) 사용자 웹브라우저 출력
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
    // CASE 1: 데이터 2가지만 웹서버로 들어오는 경우 (@RequestParam(name="title") String title, @RequestParam(name="price") Integer price) )

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
//
//        // var test = new Item();
//        // test.setTitle(title);
//        // test.setPrice(price);
//
//        return "redirect:/list";   // redirect:/list 이러면 특정 웹페이지(/list)로 유저를 강제 이동시킬 수 있다. (ajax로 요청하는 경우 이동불가능)
//    }

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    // CASE 2: 데이터 여러개가 웹서버로 들어오는 경우
    // Map 자료형 형태로 유저가 보낸 모든 데이터 변환해줌 (@RequestParam Map<String, Object> formData)
    // Map 자료형은 여러 데이터 한 변수에 넣고 싶을 때 사용함.
    @PostMapping("/add")   // URL 작명시 명사가 좋음.
    // String writePost(@RequestParam Map<String, Object> formData) {
    // String writePost(@RequestParam Map formData) {
    String writePost(@RequestParam Map<String, Object> formData) {
        Item newItem = new Item();
        newItem.setTitle(formData.get("title").toString());   // Map 자료형 변수 formData에서 key가 "title"인 value 값 문자열 변환(.toString()) 후 newItem.setTitle() 함수 호출

        // TODO: 아래와 같은 오류 메시지 출력되어 형변환 방식 변경함. (2025.08.19 minjae)
        //       (기존) 명시적 형변환 (Integer) -> (변경) Integer.valueOf(formData.get("price").toString())
        // 오류 메시지 - There was an unexpected error (type=Internal Server Error, status=500).
        //             class java.lang.String cannot be cast to class java.lang.Integer (java.lang.String and java.lang.Integer are in module java.base of loader 'bootstrap')
        //             java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Integer (java.lang.String and java.lang.Integer are in module java.base of loader 'bootstrap')
        //	           at com.apple.shop.ItemController.writePost(ItemController.java:147)
        // 참고 URL - https://claude.ai/chat/8f335633-2258-45f2-b12f-ee216b920c58
        // newItem.setPrice((Integer)formData.get("price"));   // Map 자료형 변수 formData에서 key가 "price"인 value 값 정수 변환(Integer) 후 newItem.setTitle() 함수 호출
        newItem.setPrice(Integer.valueOf(formData.get("price").toString()));   // Map 자료형 변수 formData에서 key가 "price"인 value 값 문자열 변환(.toString()) 및 정수 변환(Integer.valueOf) 후 newItem.setPrice() 함수 호출

        itemRepository.save(newItem);

        return "redirect:/list";   // redirect:/list 이러면 특정 웹페이지(/list)로 유저를 강제 이동시킬 수 있다. (ajax로 요청하는 경우 이동불가능)

        // TODO: 아래 주석친 코드 필요시 참고 (2025.08.19 minjae)
        // var test = new HashMap<>();
        // HashMap<String, Object> test = new HashMap<>();
        // Map<String, Object> test = new HashMap<>();
        // test.put("name", "kim");
        // test.put("age", 20);
        // System.out.println(test);
        // System.out.println(test.get("name"));  // Map 자료형 변수 test에서 key가 "name"인 value 값 출력
        // System.out.println(test.get("age"));  // Map 자료형 변수 test에서 key가 "age"인 value 값 출력

        // System.out.println(formData);

        // Map<String, Object> test = new HashMap<>();
        // test.put("title", title);  자료이름: "title" 자료값: title
        // test.put("price", price);  자료이름: "price" 자료값: price

        // itemRepository.save(test);
    }

    // 웹서버 API 작성 예시
    // @GetMapping("/list")   // URL 작명시 명사가 좋음.
    // String list(){
    //     return "list.html";
    // }
}