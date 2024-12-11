package com.kcanmin.di.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Stream.of(1,2,3,4,5).toList());
        log.info(list);

        Comparator<Integer> c = (a, b) -> b - a;
        // comparator 는 interface이다.
        list.sort((a, b)-> b-a);
        log.info(list);


        // 익명 클래스 호출 
        // 마이인터는 불완전한 상태, 추상메서드를 구현해줘야 함
        MyInter inter = new MyInter() {
            public Integer run(String str){

                return 10;
            }

            public Integer de(String str){
                return 20;
            }
        };

        log.info(inter.de(""));
        log.info(inter.run(""));

        log.info(MyInter.sm("30"));

        MyInter myInter2 = a -> 50;
        // MyInter myInter2 = (a) -> {return 50;};
    }
}
