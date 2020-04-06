package com.lby.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @author macro
 * @Created on 2020-04-06
 */
public class VolatileDemo {
    //不加volatile程序会死循环
    //加volatile可以保证可见性

    private volatile static int num=0;
    public static void main(String[] args) {//main
       new Thread(()->{//线程一对主内存的变化是不知道的
           while (num==0){

           }
       }).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num=1;
        System.out.println(num);
    }
}
