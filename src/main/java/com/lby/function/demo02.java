package com.lby.function;

import java.util.function.Predicate;

/**
 * 断定型接口：有一个输入参数，返回类型为boolean
 * @author macro
 * @Created on 2020-04-05
 */
public class demo02 {
    public static void main(String[] args) {
        /*Predicate predicate=new Predicate() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        };*/
        Predicate<String> predicate=str->{
            return false;
        };
        System.out.println(predicate.test("a"));
    }
}
