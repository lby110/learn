package com.lby.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author macro
 * @Created on 2020-04-05
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();//创建单线程
        ExecutorService executorService = Executors.newFixedThreadPool(3);//创建固定线程
        ExecutorService executorService = Executors.newCachedThreadPool();//创建一个可根据需要创建新线程的线程池

        try {
            for (int i = 0; i < 19; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();//线程池用完关闭线程池
        }

    }
}
