package com.lby.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁问题：关于锁的八个问题
 * 1.标准情况下，两个线程先打印发短信还是打电话？1.发短信 2.打电话
 * 1.发短信延迟4s，两个线程先打印发短信还是打电话？1.发短信 2.打电话
 * 为什么会导致1、2情况呢？因为有锁的存在
 * synchronized：锁的对象是方法的调用者。由于phone只有一个所以谁先拿到就谁先执行
 * @author macro
 * @Created on 2020-04-03
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone=new Phone();
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
            phone.call();
        },"B").start();
    }
}
class Phone{
    public synchronized void sendEms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
}
