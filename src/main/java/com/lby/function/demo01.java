package com.lby.function;

import java.util.function.Function;

/**
 * Function函数式接口，有一个输入参数，一个输出参数
 * 只要是函数式接口都可以用lambda表达式
 * @author macro
 * @Created on 2020-04-05
 */
public class demo01 {
    public static void main(String[] args) {
        /*Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };*/
        //lambda表达式
        Function<String, String> function = str -> {
            return str;
        };
        System.out.println(function.apply("s"));
    }
}
