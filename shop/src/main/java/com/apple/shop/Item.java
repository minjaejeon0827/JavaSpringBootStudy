package com.apple.shop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

@Entity   // @Entity(독립체 or 테이블 의미) 붙이면 "Item" 이름으로 DBeaver - MySQL - 데이터베이스 'Shop'에 데이터 테이블 하나 생성해줌
@Getter   // Lombok 라이브러리 사용해서 @Getter @Setter를 변수나 클래스 위에 붙이면 getter, setter 함수를 자동으로 편리하게 만들어준다.
@Setter   // Lombok 라이브러리 사용해서 @Getter @Setter를 변수나 클래스 위에 붙이면 getter, setter 함수를 자동으로 편리하게 만들어준다.
@ToString // 롬복(Lombok) 라이브러리 @ToString 사용하면 해당 Item @Entity 클래스 안에 속하는 .toString() 역할의 함수를 알아서 만들어준다.
public class Item {
  // 데이터들끼리 서로 구분하기 위한 id 컬럼은 항상 @Id 라고 표기
  // id 컬럼에는 @Id 붙이라고 강요함. (@Id 붙이면 자동으로 unique = true 부여됨.)
  // @GeneratedValue 붙여두면 상품을 하나 추가(컬럼 - "title", "price")할 때마다 알아서 id 컬럼값이 1씩 증가하는 정수가 입력된다. (SQL - auto increment 기능과 유사함.)
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  // public Long id;   // 상품 구분용 유니크(unique) 번호(정수)를 부여하기 위한 컬럼 id 생성
  private Long id;   // 상품 구분용 유니크(unique) 번호(정수)를 부여하기 위한 컬럼 id 생성

  // @Column 사용해서 제약조건 부여가능
  // nullable = false - 해당 "title" 컬럼에 데이터가 비어있으면 테이블에 입력막아주세요 (빼먹으면 안되는 중요한 데이터가 들어가야 하는 컬럼들에는 이처럼 @Column 사용해서 컬럼 제약조건 설정 필수)
  // unique = true - 해당 "title" 컬럼값이 유니크(unique) 하지 않으면 테이블에 입력막아주세요
  // @Column(nullable = false, unique = true)
  // columnDefinition = "TEXT" - 255자 이상의 매우 긴 문자를 저장하고 싶으면 MySQL같은 경우 text 타입 사용해서 컬럼 타입 강제로 지정가능
  // 참고사항 - String title; 선언시 문자 255자 까지만 저장할 수 있는 컬럼 "title"이 생성됨. (VARCHAR(255))
  // @Column(columnDefinition = "TEXT")
  // @Column(length = 1000) - 문자 1000자 미만 저장할 수 있는 컬럼 "title"이 생성됨.
  @Column(length = 1000)
  // public String title;
  private String title;
  // protected String title;
  // static String title;
  // public Integer price;  // 데이터 테이블에 속하는 컬럼용 변수에는 자료형 int 말고 Integer 사용 강요함. (int/Integer: +-20억까지 저장가능)
  private Integer price;  // 데이터 테이블에 속하는 컬럼용 변수에는 자료형 int 말고 Integer 사용 강요함. (int/Integer: +-20억까지 저장가능)
  // public Long price;  // 데이터 테이블에 속하는 컬럼용 변수에는 자료형 long 말고 Long 사용 강요함. (long/Long: +-900경까지 저장가능)
  // Long 타입 숫자 붙이기 예시) 100L

  // 롬복(Lombok) 라이브러리 사용 안 하고 setter 함수 직접 구현
  // public void setTitle(String title) {
  //  // if 255 자 이하면 title에 저장해주세요~
  //  this.title = title;
  // }

  // 롬복(Lombok) 라이브러리 사용 안 하고 setter 함수 직접 구현
  // public void setPrice(Integer price) {
  //  // if 누가 Integer 넣으면 Long으로 바꿔줘라
  //  this.price = price;
  // }

  // 롬복(Lombok) 라이브러리 사용 안 하고 toString 함수 직접 구현
  // public String toString(){
  //   return this.title + this.price;
  // }
}

// extends 사용해서 상속받는 클래스
class 클래스1 extends Item {

}

// Item 클래스에 속하는 변수에 public 붙이면 해당 Item 클래스 object(객체) 생성시(나중에 object 뽑은 후에)
// public 붙인 변수들을 object.title 이렇게 자유롭게 점찍어서 출력하고 수정(변경)하고 그럴 수 있다.
// new Item().id
// new Item().title
// new Item().price

// 주의사항 - 클래스에 속하는 변수에 public 안 붙이고 아무것도 없으면 같은 폴더내의 클래스에서만 사용가능하다.

// Item 클래스에 속하는 변수에 private 붙이면 해당 Item 클래스 object(객체) 생성시(나중에 object 뽑은 후에)
// private 붙인 변수들을 object.title 이렇게 자유롭게 점찍어서 출력하고 수정(변경) 불가하다.
// 그럼 어떻게 나중에 변수를 수정(변경)하고 싶을 때 어떻게 쓰냐고요?
// 변수를 출력하고 수정(변경)해주는 getter 아니면 setter 함수를 만들어서 그 함수를 쓴다.