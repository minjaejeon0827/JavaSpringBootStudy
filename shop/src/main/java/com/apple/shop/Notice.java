package com.apple.shop;

import jakarta.persistence.*;

// TODO: ParseException 사용하기 위해 import java.text.ParseException; import 처리 (2025.08.11 minjae)
// 참고 URL - https://docs.oracle.com/javase/8/docs/api/java/text/ParseException.html
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity   // @Entity(독립체 or 테이블 의미) 붙이면 "Notice" 이름으로 DBeaver - MySQL - 데이터베이스 'Shop'에 데이터 테이블 하나 생성해줌
public class Notice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String date;
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
