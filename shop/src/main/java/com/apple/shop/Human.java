package com.apple.shop;

public class Human {
    private String 이름;
    private Integer 나이 = 0;

    public String 이름가져오기() {
        return this.이름;
    }

    public void 이름설정(String 이름) {
        this.이름 = 이름;
    }

    public Integer 나이가져오기() {
        return this.나이;
    }

    public void 나이설정(Integer 나이) {
        // if(나이 >= 100 || 나이 < 0) {
        //    System.out.println("나이설정 불가 - 사유: 나이설정 범위 초과");
        //    return;
        // }
        if (나이 > 0 && 나이 < 100) {
          this.나이 = 나이;
        }
    }

    public void 한살더하기() {
        this.나이 += 1;
    }
}
