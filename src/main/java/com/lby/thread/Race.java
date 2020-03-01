package com.lby.thread;

public class Race implements Runnable {
    private static String winnder;

    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (Thread.currentThread().getName().equals("兔子")){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean flag=this.gameOver(i);
            if (flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"跑了"+i+"步");
        }
    }

    public boolean gameOver(int steps) {
        if (winnder!=null){
            return true;
        }{
            if (steps>=100){
                winnder=Thread.currentThread().getName();
                System.out.println("winnder is:"+winnder);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race=new Race();
        new Thread(race,"兔子").start();
        new Thread(race,"乌龟").start();
    }
}
