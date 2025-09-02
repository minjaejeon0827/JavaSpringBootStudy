package com.apple.shop;

// Q. 왜 ItemService라고 작명함?
// - 원래 웹서버가 유저에게 데이터 전송 전에 이거저거 검사하거나 DB 데이터 입출력 처리해주는 비즈니스 로직 클래스 같은걸 Service라고 보통 부른다.

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

// 참고 - 테이블에서 데이터 입출력하려면 항상 3-step 이 필요하다.
// 1. repository라는걸 만들고 (예) interface ItemRepository.java
// 2. DB입출력 원하는 클래스에서 repository를 등록하고 (예) private final ItemRepository itemRepository;
// 3. DB입출력문법 가져다가 쓰면 된다. (예) itemRepository.save(item);

@Service   // Service 클래스엔 기본으로 붙여야함.
@RequiredArgsConstructor // - Lombok 문법
public class ItemService {

    private final ItemRepository itemRepository;

    // 아이템 저장
    public void saveItem(String title, Integer price) {
        // new Item()으로 생성한 변수에다가 여러가지 정보(데이터)를 채운 다음에
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);

        // 리포지토리.save() 함수 소괄호 안에 new Item()으로 생성한 변수를 넣으면 그 정보(데이터)들을 채워서 행을 하나 테이블에 데이터로 추가해준다.
        itemRepository.save(item);
    }

    // TODO: 글수정 기능 ItemService 함수 saveItem 구현 (2025.09.02 minjae)
    // 참고 URL - https://claude.ai/chat/58e6617a-58e6-4f8a-a581-1f137c4ce8b0
    // public void saveItem(Long id, String title, Integer price) {
    public void saveItem(Item item) {
        // new Item()으로 생성한 변수에다가 여러가지 정보(데이터)를 채운 다음에
        // Item item = new Item();
        // item.setId(id);
        // item.setTitle(title);
        // item.setPrice(price);

        // 리포지토리.save() 함수 소괄호 안에 함수 파라미터로 받은 변수(item)를 넣으면 해당 변수(item)에 속한 id 값과 같은 테이블에 있는 기존 정보(데이터)를 수정해준다.
        itemRepository.save(item);
    }

    // 아이템 리스트 가져오기
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    // 특정 id 값을 가진 아이템 가져오기
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }
}
