package com.apple.shop;

import lombok.Getter;
import lombok.Setter;

@Getter   // Lombok 라이브러리 사용해서 @Getter @Setter를 변수나 클래스 위에 붙이면 getter, setter 함수를 자동으로 편리하게 만들어준다.
@Setter   // Lombok 라이브러리 사용해서 @Getter @Setter를 변수나 클래스 위에 붙이면 getter, setter 함수를 자동으로 편리하게 만들어준다.
public class Test {
    private Integer age;
    private String name;
    public void 한살더하기(){
        this.age = this.age + 1;
    }

    public void 나이설정(Integer a){
        if (a > 0 && a < 100) {
            this.age = a;
        }
    }
}
