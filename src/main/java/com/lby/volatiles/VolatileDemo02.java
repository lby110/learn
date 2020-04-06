package com.lby.volatiles;

/**
 * volatile不保证原子性
 * @author macro
 * @Created on 2020-04-06
 */
public class VolatileDemo02 {
    private volatile static int num=0;
    public static void add(){
        num++;//不是一个原子性操作
    }
    public static void main(String[] args) {//main
        //理论上结果为20000
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j <2000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+num);
    }
}
