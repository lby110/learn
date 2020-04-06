package com.lby.qu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 抛出异常
 *
 * @author macro
 * @Created on 2020-04-04
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.test3();
    }

    /**
     * 抛出异常
     */
    public void test1() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.element());//获取队列的头
        // System.out.println(blockingQueue.add("c"));//添加第一个元素：java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // System.out.println(blockingQueue.remove());//java.util.NoSuchElementException
    }

    /**
     * 不抛出异常
     */
    public void test2() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("c"));
        blockingQueue.peek();//获取但不移除此队列的头；如果此队列为空，则返回 null。
        // System.out.println(blockingQueue.add("c"));//添加第一个元素：java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //System.out.println(blockingQueue.remove());//java.util.NoSuchElementException
    }
    /**
     * 超时等待
     */
    public void test3() throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a");
        blockingQueue.offer("a");
        blockingQueue.offer("a");
        blockingQueue.offer("d",2, TimeUnit.SECONDS);
        // System.out.println(blockingQueue.add("c"));//添加第一个元素：java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
        //System.out.println(blockingQueue.remove());//java.util.NoSuchElementException
    }
}

