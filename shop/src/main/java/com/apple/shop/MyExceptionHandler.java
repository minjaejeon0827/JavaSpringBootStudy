package com.apple.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// @ControllerAdvice를 예외처리 클래스(MyExceptionHandler) 상단에 붙이고
// 해당 예외처리 클래스(MyExceptionHandler) 안에 아까 그 @ExceptionHandler를 넣는것이다.
// 이러면 웹 프로젝트 폴더 (예) shop 안에 속한 모든 컨트롤러 클래스 (예) ItemController.java 등등... 에서 에러가 나는 경우
// 여기 있는 코드(public ResponseEntity<String> handler() { ... })가 대신 실행된다.
// 모든 컨트롤러 파일 @Controller 파일의 에러를 캐치하려면 아래처럼 @ControllerAdvice 붙여서 "MyExceptionHandler" 클래스 구현
//@ControllerAdvice
//public class MyExceptionHandler {
//    // 유저가 웹브라우저 URL 주소 입력시 숫자가 아닌 문자를 입력한 경우
//    // 올바른 예시 - http://localhost:8080/test2/1
//    // 에러 발생한 예시 - http://localhost:8080/test2/aaaaa
//    // MethodArgumentTypeMismatchException.class 설정시
//    // 웹 프로젝트 폴더 (예) shop 안에 속한 모든 컨트롤러 클래스에서
//    // 유저가 웹브라우저 URL 주소 입력시 숫자가 아닌 문자를 입력한 경우 발생한 에러에 아래 handler1 함수에 속한 코드 실행
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<String> handler1() {
//        return ResponseEntity.status(400).body("에러남");
//    }
//
//    @ExceptionHandler(Exception.class)  // Exception.class 설정시 웹 프로젝트 폴더 (예) shop 안에 속한 모든 컨트롤러 클래스에서 발생한 모든 에러에 아래 handler 함수에 속한 코드 실행
//    public ResponseEntity<String> handler() {
//        return ResponseEntity.status(400).body("에러남");
//    }
//}
