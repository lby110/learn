package com.lby.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author macro
 * @Created on 2020-04-04
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });
        for (int i = 1; i <=7; i++) {
            final int temp=i;
            new Thread(()->{
                //由于这里不能直接拿到i，所以我们上面定义一个final的temp来暂时存储一下i
                System.out.println("第"+temp+"龙珠");
                try {
                    cyclicBarrier.await();//等待计数器变成7，继续往下走
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
