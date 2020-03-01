package com.lby.thread;

public class TestThread2 implements Runnable {
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("多线程学习"+i);
        }
    }

    public static void main(String[] args) {
        //1.
        TestThread2 testThread2=new TestThread2();
        new Thread(testThread2).start();
        for (int i = 0; i < 20; i++) {
            System.out.println("来学习"+i);
        }
    }

}
