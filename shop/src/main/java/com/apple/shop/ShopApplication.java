// * intellij community 에디터 설치 방법
// 참고 URL - https://blog.naver.com/PostView.naver?blogId=djusti&logNo=223143383341
// 참고 2 URL - https://priming.tistory.com/50

// * spring initializr 이용하여 프로젝트 생성
// 참고 URL - https://start.spring.io/
// 참고 2 URL - https://guns23.tistory.com/8

package com.apple.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

    // 리턴타입 함수이름() {
	static void 함수이름() {
		// 코드~~
        // 코드~~
        // 코드~~
		// return "ㅇ";
	}

    // 프로젝트 "shop" 시작시 프로그램 시작 지점 (main 메서드)
	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);   // Spring boot로 만든 웹서버 띄우기
		함수이름();

		// String 변수명 = 값;
        // String lover = "김말자";
		// int age = 20;
		// var lover = "김말자";
        // final String lover = "김말자";
		// var age = 20;
        // System.out.println(lover);
		// final int wifeCount = 1;
		// System.out.println(wifeCount);

		// if (3 > 1) {
		//	 실행할코드~~
		// }

		// for (int i = 0; i < 3; i++) {
		//	   실행할코드~~
		// }
	}

}

//[코딩애플] 쉽게 배우는 Spring Boot & JPA
//Part 1
//1강 - Spring Boot 왜 쓰는데
//2강 - 개발환경 셋팅 / Spring Boot 3 프로젝트 생성

// TODO: 아래와 같은 오류 메시지 출력시 오류 원인은 "Dependency requires at least JVM runtime version 17. This build uses a Java 16 JVM." 이다.
//       해당 오류 원인은 즉, 현재 Java 16을 사용하고 있는데, spring-boot-gradle-plugin:3.4.7 은 최소 Java 17 이상이 필요하다는 의미이다.
//       하여 아래와 같은 2가지 방법을 진행하여 오류를 해결한다. (2025.07.10 minjae)
// 1. Java 24 이상 설치
//    Java 24 또는 그 이상 버전을 설치합니다.
//    Adoptium에서 OpenJDK 24 다운로드 가능
//
// 2. JAVA_HOME 환경변수 변경
//    Java 24로 설정되었는지 확인 및 설정:
// (예) 변수 이름 - JAVA_HOME / 변수 값 - C:\Users\bhjeon\.jdks\openjdk-24.0.1
// 참고 URL - https://chatgpt.com/c/686f03e4-33d0-8010-862f-03608e1e4ea3

// 오류 메시지
//A problem occurred configuring root project 'shop'.
//		> Could not resolve all artifacts for configuration 'classpath'.
//		> Could not resolve org.springframework.boot:spring-boot-gradle-plugin:3.4.7.
//Required by:
//root project : > org.springframework.boot:org.springframework.boot.gradle.plugin:3.4.7
//		> Dependency requires at least JVM runtime version 17. This build uses a Java 16 JVM.
//
//* Try:
//		> Run this build using a Java 17 or newer JVM.
//> Run with --stacktrace option to get the stack trace.
//> Run with --info or --debug option to get more log output.
//> Run with --scan to get full insights.
//> Get more help at https://help.gradle.org.
//Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.
//You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.
//For more on this, please refer to https://docs.gradle.org/8.14.2/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.
