package com.lby.function;

import java.util.function.Supplier;

/**
 * Supplier：供给型接口，只返回不输入
 * @author macro
 * @Created on 2020-04-05
 */
public class demo03 {
    public static void main(String[] args) {
        /*Supplier<String> supplier=new Supplier<String>() {
            @Override
            public String get() {
                return "1024";
            }
        };*/
        Supplier<Integer> supplier=()->{
          return 1024;
        };
        System.out.println(supplier.get());
    }
}
