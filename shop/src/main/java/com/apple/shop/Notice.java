package com.apple.shop;

import jakarta.persistence.*;
import lombok.ToString;

// TODO: ParseException 사용하기 위해 import java.text.ParseException; import 처리 (2025.08.11 minjae)
// 참고 URL - https://docs.oracle.com/javase/8/docs/api/java/text/ParseException.html
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity   // @Entity(독립체 or 테이블 의미) 붙이면 "Notice" 이름으로 DBeaver - MySQL - 데이터베이스 'Shop'에 데이터 테이블 하나 생성해줌
// @ToString // 롬복(Lombok) 라이브러리 @ToString 사용하면 해당 Notice @Entity 클래스 안에 속하는 .toString() 역할의 함수를 알아서 만들어준다.
public class Notice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;   // notice.html 파일에서 ${i.title} 쓸 수 있게 하는 필드
  private String date;   // notice.html 파일에서 ${i.date} 쓸 수 있게 하는 필드

  // 롬복(Lombok) 라이브러리 사용 안 하고 toString 함수 직접 구현
  public String toString(){
     return this.title + this.date;
  }

  // TODO: /notice 웹페이지(notice.html) 실행시 NoticeController.java -> @GetMapping("/notice") -> String notice(Model model) 함수 실행되고
  //       notice.html 파일이 웹브라우저에 출력될 때, 아래와 같은 오류 메시지 출력되어 public getter 함수 추가 구현 (2025.08.13 minjae)
  // 오류 메시지 - Caused by: org.springframework.expression.spel.SpelEvaluationException:
  //             EL1008E: Property or field 'title' cannot be found on object of type 'com.apple.shop.Notice'
  // 참고 URL - https://chatgpt.com/c/689bface-14b4-8321-988b-618af0bafe2d

  // public getter getTitle
  public String getTitle() {
    return this.title;
  }

  // public getter getDate
  public String getDate() {
    return this.date;
  }
}

//@Entity   // @Entity(독립체 or 테이블 의미) 붙이면 "Notice" 이름으로 DBeaver - MySQL - 데이터베이스 'Shop'에 데이터 테이블 하나 생성해줌
//public class Notice {
//  // 데이터들끼리 서로 구분하기 위한 id 컬럼은 항상 @Id 라고 표기
//  // id 컬럼에는 @Id 붙이라고 강요함. (@Id 붙이면 자동으로 unique = true 부여됨.)
//  // @GeneratedValue 붙여두면 상품을 하나 추가(컬럼 - "title", "price")할 때마다 알아서 id 컬럼값이 1씩 증가하는 정수가 입력된다. (SQL - auto increment 기능과 유사함.)
//  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//  public Long id;   // 상품 구분용 유니크(unique) 번호(정수)를 부여하기 위한 컬럼 id 생성
//
//  @Column(nullable = false)
//  public String title;
//
//  @Column(nullable = false)
//  public String date;
//
//  // TODO: @Temporal(TemporalType.DATE) 사용하여 자료형 Date 타입 컬럼 date 생성 (2025.08.11 minjae)
//  // 참고 URL - https://5bong2-develop.tistory.com/222
//  // 참고 2 URL - https://chatgpt.com/c/68994985-86a8-8332-be4e-9b83224db2c5
//  //  @Temporal(TemporalType.DATE)
//  //  public Date date = new Date();
//
//  //  @Transient // DB에 저장 안 됨
//  //  private String dateKorean;
//  //
//  //  public void setDateKorean(String dateKorean) throws ParseException {
//  //    this.dateKorean = dateKorean;
//  //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 d일");
//  //    this.date = sdf.parse(dateKorean);
//  //  }
//  //
//  //  public String getDateKorean() {
//  //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 d일");
//  //    return this.date != null ? sdf.format(this.date) : null;
//  //  }
//}
