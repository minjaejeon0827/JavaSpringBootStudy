package com.apple.shop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;

@Controller   // @Controller 사용하면 springframework가 알아서 아래 코드(public class BasicController { ... }) 가져와서 ShopApplication 클래스 main 함수에 집어 넣어서 아래 웹서버 코드 실행
public class BasicController {
    // 여기서 웹서버기능 제작가능

    // 웹서버 API
    @GetMapping("/")   // 1. URL("/")로 HTTP - GET method 요청 날리면
// @ResponseBody   // html 파일(index.html)을 유저 웹브라우저로 전송하고 싶으면 @ResponseBody를 빼줘야 함.
    String hello(){
//        Thymeleaf 문법(Thymeleaf 템플릿 엔진 외부 라이브러리 설치해야 사용 가능한 문법.)을 사용하고 싶으면
//        index.html 기본 상위 폴더 경로를
//        (기존) static 폴더 
//        (변경) templates 폴더(폴더 경로 - "D:\minjae\JavaSpringBootStudy\shop\src\main\resources\templates")로 html 파일을 옮겨야 잘 동작한다.
        // 2. html 파일("index.html")을 유저에게 보내주세요.
        return "index.html";   // index.html 기본 상위 폴더 경로 (기존) static 폴더 -> (변경) templates 폴더
    }

// 웹서버 API
//    @GetMapping("/")
//    @ResponseBody
//    String hello(){
//        return "<h4>반갑습니다</h4>";
//        return "<h4>안녕하쇼</h4>";
//    }

// 웹서버 API
//    @GetMapping("/")
//    @ResponseBody
//    String hello(){
//        return "안녕하쇼";
//    }

    // 웹서버 API
    @GetMapping("/test")
// @ResponseBody   // html 파일(test.html)을 유저 웹브라우저로 전송하고 싶으면 @ResponseBody를 빼줘야 함.
    String test(){
        return "test/test.html";   // test.html 기본 상위 폴더 경로가 static 폴더 하위에 속한 test 폴더임
    }

    // 웹서버 API
    @GetMapping("/about")   // 1. URL("/about")로 HTTP - GET method 요청 날리면
    @ResponseBody
    String about(){
        // 2. 문자열("피싱사이트에요")을 유저에게 보내주세요.
        return "피싱사이트에요";
    }

    // 웹서버 API
    @GetMapping("/mypage")
    @ResponseBody
    String mypage(){
        return "마이페이지입니다";
    }

    // 웹서버 API
    @GetMapping("/date")
    @ResponseBody
    String date(){
        // 현재 날짜와 시간 구하기
//        return LocalDateTime.now().toString();
        return ZonedDateTime.now().toString();

        // TODO: 현재 날짜와 시간 구하기 (2025.07.15 minjae)
        // 참고 URL - https://greenyellow-s.tistory.com/30
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("y년 MM월 dd일 H시 m분 s초");
//        return sdf.format(date);
    }

// 웹서버 API 작성 예시
//    @GetMapping("/경로")
//    @ReponseBody
//    String hello(){
//        return "유저에게 보내줄데이터";
//    }
}
