package com.lby.thread;

public class TestYield implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始执行");
        Thread.yield();//礼让方法
        System.out.println(Thread.currentThread().getName()+"结束执行");
    }

    public static void main(String[] args) {
        TestYield testYield=new TestYield();
        new Thread(testYield,"a").start();
        new Thread(testYield,"b").start();
    }
}
