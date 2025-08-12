package com.apple.shop;  // 파일 상단에 package 파일경로;(com 폴더 -> apple 폴더 -> shop 폴더) 라고 적어줘야 다른 파일에서도 여기 있던 코드를 사용가능하다.
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
//import java.util.ArrayList;
//import java.util.List;


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
    @GetMapping("/list")
    String list(Model model) {  // Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용하기 위해 파라미터 Model model 추가

        // var result = itemRepository.findAll();   // DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 모든 데이터 꺼내주세요~ (List 타입(자료형) []으로 데이터 출력 중인 테이블 클래스 "Item" Object 형태로 데이터 가져옴)
        List<Item> result = itemRepository.findAll();   // 데이터 출력 중인 테이블 클래스 "Item"으로 List 타입(자료형) 명확하게 해서 DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 모든 데이터 꺼내주세요~ (List 타입(자료형) []으로 데이터 출력 중인 테이블 클래스 "Item" Object 형태로 데이터 가져옴)
        System.out.println(result);
        System.out.println(result.get(0));  // DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 첫째 행(get(0)) 데이터 출력 (데이터 com.apple.shop.Item@6d17b11e 출력 - Item 클래스의 Object 라는 뜻이다.)
        System.out.println(result.get(0).price);  // DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 첫째 행(get(0)) 가격(price) 데이터 출력
        System.out.println(result.get(0).title);  // DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 저장된 첫째 행(get(0)) 제목(title) 데이터 출력
        // itemRepository.save();   // DBeaver - MySQL - shop 데이터베이스 - item 데이터테이블에 데이터 저장해주세요~
        // JPA 사용해서 테이블에서 데이터 입출력하려면
        // 아래처럼 항상 3-step 이 필요하다.
        // 1. repository 만들기
        // 2. DB입출력 원하는 클래스에서 repository 등록
        // 3. DB입출력문법(함수)(repository.입출력문법함수();) 가져다가 쓰기

        // var a = new ArrayList<>();
        // ArrayList<Object> a = new ArrayList<>();  // 문자와 숫자 등 여러종류 타입(자료형)을 동시에 넣기 (또는 List<Object> b = new ArrayList<>();)
        // ArrayList<String> a = new ArrayList<>();  // 문자열 타입(자료형)
        ArrayList<Integer> a = new ArrayList<>();    // 정수형 타입(자료형)
        a.add(30);
        a.add(40);
        System.out.println(a);
        System.out.println(a.get(0));
        System.out.println(a.get(1));

        model.addAttribute("name", "비싼 바지");  // html 파일에 보내고 싶은 웹서버데이터 이름 "name", 값 "비싼 바지" 메서드 addAttribute 사용해서 집어넣기
        return "list.html";
    }

    // 웹서버 API 작성 예시
    // @GetMapping("/list")
    // String list(){
    //     return "list.html";
    // }
}