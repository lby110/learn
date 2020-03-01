package com.lby.thread;

public class TestThread4 implements Runnable {
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run...Thread" + i++);
        }
    }

    public void stop(){
        this.flag=false;
    }

    public static void main(String[] args) {
        TestThread4 thread4=new TestThread4();
        new Thread(thread4).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("main"+i);
            if (i==900){
                thread4.stop();
                System.out.println("线程停止");
            }
        }
    }
}
