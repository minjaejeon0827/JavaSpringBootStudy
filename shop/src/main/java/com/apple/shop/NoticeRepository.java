package com.apple.shop;

import org.springframework.data.jpa.repository.JpaRepository;

// 1. repository 만들기
// Google Gemini 사용자 질문 - "java spring boot 프레임워크에서 JpaRepository와 CrudRepository의 각각의 기능과 차이점에 대해 구체적으로 알려줘."
// 참고 URL - https://gemini.google.com/app/16d012e8f5315f5a

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}

// TODO: 필요시 아래 소스코드 참고 (2025.08.12 minjae)
//public interface 작명 extends JpaRepository<Entity명, id컬럼타입> {
//
//}
//
//- 파일명은 관습적으로 데이터 입출력할 Entity이름 + Repository라고 뒤에 붙여서 짓고
//- 그 안에 public interface를 만들고
//- <> 안에는 데이터 입출력할 Entity 이름과 그 Entity에 있던 id 컬럼의 타입(자료형 - Long, Integer 등등...)을 넣어주자.
