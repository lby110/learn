package com.lby.thread;

public class TestSleep {
    public static void test() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num<=0){
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        test();
    }
}
