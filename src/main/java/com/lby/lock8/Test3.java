package com.lby.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁问题：关于锁的八个问题
 * 5.增加两个静态同步方法，两个线程先打印发短信还是打电话？1.发短信 2.打电话
 * static是静态方法，类一加载就有了。锁的是Class。Phone3唯一的一个Class对象
 * @author macro
 * @Created on 2020-04-03
 */
public class Test3 {
    public static void main(String[] args) {
        Phone3 phone=new Phone3();
        Phone3 phone2=new Phone3();
        new Thread(()->{
            phone.sendEms();
        },"A").start();
        try {
            //延时1s
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            phone2.call();
        },"B").start();
    }
}

class Phone3{
    public static synchronized void sendEms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public static synchronized void call(){
        System.out.println("打电话");
    }
    public void hello(){
        System.out.println("hello");
    }
}
