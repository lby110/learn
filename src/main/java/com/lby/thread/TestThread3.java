package com.lby.thread;

public class TestThread3 implements Runnable{
    private int ticketNums=10;
    public void run() {
        //票数
        while (true){
            if (ticketNums<=0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNums--+"票");
        }
    }

    public static void main(String[] args) {
        TestThread3 t1=new TestThread3();
        new Thread(t1,"小明").start();
        new Thread(t1,"小李").start();
        new Thread(t1,"黄牛").start();
    }
}
