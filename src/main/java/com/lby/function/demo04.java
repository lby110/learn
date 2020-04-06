package com.lby.function;

import java.util.function.Consumer;

/**
 * Consumer：消费型接口，不返回
 * @author macro
 * @Created on 2020-04-05
 */
public class demo04 {
    public static void main(String[] args) {
        /*Supplier<String> supplier=new Supplier<String>() {
            @Override
            public String get() {
                return "1024";
            }
        };*/
        Consumer<Integer> consumer=(Integer str)->{
            System.out.println(str);
        };
       consumer.accept(1);
    }
}
