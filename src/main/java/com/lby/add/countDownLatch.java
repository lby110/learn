package com.lby.add;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 *
 * @author macro
 * @Created on 2020-04-03
 */
public class countDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"go out");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("close door");
    }
}
