package com.kcanmin.aop.ex03;


// before, around, afterReturn
// throws, after(finally) // after는 정상종료가 되던, 예외가발생하던 무조건 실행한다.
public interface Mart {
    void getProduct(String name);
}
