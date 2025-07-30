package com.apple.shop.item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller   // @Controller 사용하면 springframework가 알아서 아래 코드(public class ItemController { ... }) 가져와서 ShopApplication 클래스 main 함수에 집어 넣어서 아래 웹서버 코드 실행
public class ItemController {
    // 여기서 웹서버기능 제작가능

    // 웹서버 API - Thymeleaf 템플릿 엔진 사용해서 웹서버데이터를 html에 박아서 보내주는 웹서버 API
//    @GetMapping("/list")
//    String list(Model model) {
//        model.addAttribute("name", "홍길동");
//        return "list.html";
//    }

    // 웹서버 API 작성 예시
//    @GetMapping("/list")
//    String list(){
//        return "list.html";
//    }

}