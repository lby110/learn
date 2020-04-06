package com.lby.executors;

import java.util.concurrent.*;

/**
 * new ThreadPoolExecutor.AbortPolicy()：银行人满了，还没有人进来，不处理这个人。直接抛出异常RejectedExecutionException
 * new ThreadPoolExecutor.CallerRunsPolicy():哪里来回哪里
 * new ThreadPoolExecutor.DiscardPolicy():队列满了，丢弃任务。不会抛出异常
 * new ThreadPoolExecutor.DiscardOldestPolicy():队列满了和最早的竞争，不会抛出异常
 * @author macro
 * @Created on 2020-04-05
 */
public class ExecutorsDemo2 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());//银行人满了，还没有人进来，不处理这个人。直接抛出异常RejectedExecutionException

        try {
            for (int i = 0; i < 12; i++) {
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }
}
