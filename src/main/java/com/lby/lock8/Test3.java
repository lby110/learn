package com.lby.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁问题：关于锁的八个问题
 * 3.增加一个普通方法，两个线程先打印发短信还是hello？1.hello 2.发短信
 * hello没有锁，不是同步方法。不受锁的影响
 * 4.两个对象，执行两个同步方法，发短信还是打电话？1.打电话 2.发短信
 * synchronized：锁的对象是方法的调用者。这里是两个调用者，两把锁
 * @author macro
 * @Created on 2020-04-03
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone=new Phone2();
        Phone2 phone2=new Phone2();
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

class Phone2{
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
    public void hello(){
        System.out.println("hello");
    }
}
