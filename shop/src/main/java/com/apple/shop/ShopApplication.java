// * intellij community 에디터 설치 방법
// 참고 URL - https://blog.naver.com/PostView.naver?blogId=djusti&logNo=223143383341
// 참고 2 URL - https://priming.tistory.com/50

// * spring initializr 이용하여 프로젝트 생성
// 참고 URL - https://start.spring.io/
// 참고 2 URL - https://guns23.tistory.com/8

// 코드 수정 후 재컴파일 키보드 단축키 Ctrl + F9
// 디버그 터미널 출력창 키보드 단축키 Alt + 4 / Alt + 5 둘 중 하나 입력

package com.apple.shop;   // 파일 상단에 package 파일경로;(com 폴더 -> apple 폴더 -> shop 폴더) 라고 적어줘야 다른 파일에서도 여기 있던 코드를 사용가능하다.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// SpringBoot는 내부적으로 아파치 톰캣(Apache Tomcat) 웹서버 엔진을 이용하며 기본적으로 8080 포트에 띄워준다.
// 아래 URL 주소로 웹페이지 열면 아래와 같은 404 페이지가 뜨는 이유는 WebApplication.java 소스파일에서 내부적으로 코드를 짜서 개발하지 않았기 때문이다.
// http://localhost:8080/
// Whitelabel Error Page
// This application has no explicit mapping for /error, so you are seeing this as a fallback.
// Tue Jul 22 08:34:49 KST 2025
// There was an unexpected error (type=Not Found, status=404).

// 포트(Port)란 내 컴퓨터에 다른 사람들이 접속할 수 있게 만든 구멍이다.
// 컴퓨터 하나당 포트(Port)는 약 2만개 있다.
// (예) 내 컴퓨터에 다른 사람들이 접속할 수 있게 포트(Port) 8080 만들어 놓고
//      다른 사람들이 포트(Port) 8080으로 접속해면 내 컴퓨터에서 웹페이지 보내주면 그게 웹서버이다.

@SpringBootApplication
// 접근 제어자 "public" 없으면 같은 폴더(패키지) 안에서만 해당 클래스 사용가능
// 접근 제어자 "public" 붙이면 다른 폴더(패키지) 에서도 해당 클래스 사용 가능
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

		// throw new Exception();   // 강제로 에러 처리

		var object = new Human();
		object.한살더하기();
		System.out.println(object.나이가져오기());
		object.나이설정(12);
		System.out.println(object.나이가져오기());
		object.나이설정(-10);

		var a = new Test();
		a.setName("홍길동");
		a.setAge(60);
		a.나이설정(20);
		a.한살더하기();
		System.out.println(a.getAge());  // 나이 21 출력

		// var test = new Friend("park");
		// System.out.println(test.name);

		// var test2 = new Friend("lee");
		// System.out.println(test2.name);

		// new Friend("kim");
        // new Friend().age;

		// Test test = new Test();
		// test.name = "dsfsd";

		// var test = new Test();
		// test.hello();

		// System.out.println(test.name);

		// 함수이름();

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

// 접근 제어자 "public" 없으면 같은 폴더(패키지) 안에서만 해당 클래스 사용가능
// 접근 제어자 "public" 붙이면 다른 폴더(패키지) 에서도 해당 클래스 사용 가능
//class Test {
//	String name = "kim";
//	void hello() {
//		System.out.println("안녕");
//	}
//}

// 접근 제어자 "public" 없으면 같은 폴더(패키지) 안에서만 해당 클래스 사용가능
// 접근 제어자 "public" 붙이면 다른 폴더(패키지) 에서도 해당 클래스 사용 가능
class Friend {
	String name = "kim";
	int age = 20;
	// Friend(String a) {
	//	   this.name = a;
	// }
	Friend(String 구멍) {
		// this.name = "kim";  // this는 새로 생성될 object를 뜻함.  새로 생성될 object(this) 그 안의 name 변수에 "kim" 넣어주셈
		this.name = 구멍;
	}
}

//class Friend2 {
//	String name = "park";
//	int age = 20;
//}

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





// * intellij community 에디터 설치 방법
// 참고 URL - https://blog.naver.com/PostView.naver?blogId=djusti&logNo=223143383341
// 참고 2 URL - https://priming.tistory.com/50

// * spring initializr 이용하여 프로젝트 생성
// 참고 URL - https://start.spring.io/
// 참고 2 URL - https://guns23.tistory.com/8

// 코드 수정 후 재컴파일 키보드 단축키 Ctrl + F9
// 디버그 터미널 출력창 키보드 단축키 Alt + 4 / Alt + 5 둘 중 하나 입력

// *** 자바 공식문서 읽는 방법 ***
//참고 URL - https://opentutorials.org/course/3930/26665
//참고 2 URL - https://doodreamcode.tistory.com/115
//참고 3 URL - https://isaac-christian.tistory.com/entry/Java-official-document-%ED%99%9C%EC%9A%A9

// 자바 공식문서 웹사이트 목록
// 참고 URL - https://velog.io/@dev-mage/java-official-documents-list
//Java Documentation
//참고 URL - Java Platform, Standard Edition Documentation - Releases
//		  https://docs.oracle.com/en/java/javase/index.html

//Java 업데이트 정리
//참고 URL - Java Language Updates
//		https://docs.oracle.com/en/java/javase/18/language/java-language-changes.html#GUID-6459681C-6881-45D8-B0DB-395D1BD6DB9B

//Java 언어, JVM 명세
//참고 URL - Java SE Specifications
//		https://docs.oracle.com/javase/specs/

//	- Java 18(최신) JVM 개요
//	참고 URL - Java Virtual Machine Guide
//			https://docs.oracle.com/en/java/javase/18/vm/java-virtual-machine-technology-overview.html

//JDK 릴리즈 노트
//참고 URL - JDK Release Notes
//		https://www.oracle.com/java/technologies/javase/jdk-relnotes-index.html

//	- JDK 18 도구 명세
//	참고 URL - Java Development Kit Version 18 Tool Specifications
//			https://docs.oracle.com/en/java/javase/18/docs/specs/man/index.html

//	- Modular JDK(Project Jigsaw: 직소 프로젝트) - Java 9에서 도입된 모듈화 시스템
//	참고 URL - Project Jigsaw
//			https://openjdk.org/projects/jigsaw/

//*** Java API 명세 ***
//	- Java 8
//	참고 URL - Java Platform SE 8
//		https://docs.oracle.com/javase/8/docs/api/
//
//	- Java 19(최신)
//	참고 URL - Overview (Java SE 19 & JDK 19 [build 6])
//		https://download.java.net/java/early_access/loom/docs/api/

//Java 개발자 센터
//참고 URL - Dev.java: The Destination for Java Developers
//		https://dev.java/

//Java 튜토리얼
//참고 URL - Learn Java - Dev.java
//		https://dev.java/learn/

//Java 플랫폼 개요
//참고 URL - Java™ Platform Overview
//		https://docs.oracle.com/javase/8/docs/technotes/guides/index.html#langutil

//Java 튜토리얼(Java 8을 기준으로 제작됨)
//참고 URL - The Java™ Tutorials
//		https://docs.oracle.com/javase/tutorial/index.html

//Java 소개
//	간략 소개
//	참고 URL - About the Java Technology
//		https://docs.oracle.com/javase/tutorial/getStarted/intro/definition.html
//
//	1996년에 발표된 공식 보고서 - 첫 출시 즈음이라 그런지 C, C++과 비교하여 작성된 내용이 많음
//	참고 URL - The Java Language Environment: Contents
//		https://www.oracle.com/java/technologies/language-environment.html
//
//	Java 공식 보고서 목록
//	참고 URL - Java SE Reference White Papers
//		https://www.oracle.com/java/technologies/javase/javase-whitepapers.html

//Java 필수 개념 소개
//참고 URL - Trail: Learning the Java Language
//		https://docs.oracle.com/javase/tutorial/java/index.html
//
//Java 8 개발자 가이드
//참고 URL - Java Platform Standard Edition 8 Documentation
//		https://docs.oracle.com/javase/8/docs/

//Java 8 도구와 유틸리티 설명
//참고 URL - JDK Development Tools
//		https://docs.oracle.com/javase/8/docs/technotes/tools/index.html#general

//Java 8 런처(JDK의 java 실행 파일)가 실행되는 간략한 과정
//참고 URL - How Classes are Found
//		https://docs.oracle.com/javase/8/docs/technotes/tools/findingclasses.html

