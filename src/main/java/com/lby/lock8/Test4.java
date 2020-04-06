package com.lby.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁问题：关于锁的八个问题
 * 6.增加一个静态同步方法，一个普通同步方法，一个对象，两个线程先打印发短信还是打电话？1.打电话 2.发短信
 * 7.增加一个静态同步方法，一个普通同步方法，两个对象，两个线程先打印发短信还是打电话？1.打电话 2.发短信
 *
 * @author macro
 * @Created on 2020-04-03
 */
public class Test4 {
    public static void main(String[] args) {
        Phone4 phone=new Phone4();
        Phone4 phone2=new Phone4();
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

class Phone4{
    //静态同步代码。锁的是Class
    public static synchronized void sendEms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    //锁的是调用者
    public  synchronized void call(){
        System.out.println("打电话");
    }
    public void hello(){
        System.out.println("hello");
    }
}
