package com.apple.shop;  // 파일 상단에 package 파일경로;(com 폴더 -> apple 폴더 -> shop 폴더) 라고 적어줘야 다른 파일에서도 여기 있던 코드를 사용가능하다.
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller   // @Controller 사용하면 springframework가 알아서 아래 코드(public class ItemController { ... }) 가져와서 ShopApplication 클래스 main 함수에 집어 넣어서 아래 웹서버 코드 실행
// 접근 제어자 "public" 없으면 같은 폴더(패키지) 안에서만 해당 클래스 사용가능
// 접근 제어자 "public" 붙이면 다른 폴더(패키지) 에서도 해당 클래스 사용 가능
public class ItemController {
    // 여기서 웹서버기능 제작가능

    // 웹서버 API - Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
    @GetMapping("/list")
    String list(Model model) {  // Thymeleaf 템플릿 엔진(Thymeleaf 문법) 사용하기 위해 파라미터 Model model 추가
        model.addAttribute("name", "비싼 바지");  // html 파일에 보내고 싶은 웹서버데이터 이름 "name", 값 "비싼 바지" 메서드 addAttribute 사용해서 집어넣기
        return "list.html";
    }

    // 웹서버 API 작성 예시
    // @GetMapping("/list")
    // String list(){
    //     return "list.html";
    // }
}