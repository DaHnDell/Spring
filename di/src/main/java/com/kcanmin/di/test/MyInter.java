package com.kcanmin.di.test;

@FunctionalInterface
public interface MyInter {
    Integer run(String str);
    // Integer run2(String str);

    default Integer de(String str){
        return Integer.parseInt(str);
    }

    static Integer sm(String str){
        return Integer.parseInt(str);
    }

    // 인터페이스로 생성된 메서드들은 모두 public이다
    // 함수형 인터페이스가 되려면 단 하나의 추상 메서드만을 가져야 한다.
}
