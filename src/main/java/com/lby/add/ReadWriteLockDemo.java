package com.lby.add;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author macro
 * @Created on 2020-04-04
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        books book = new books();
        for (int i = 0; i < 6; i++) {
            final int temp = i;
            new Thread(() -> {
                book.write(String.valueOf(temp), "temp" + temp);
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 6; i++) {
            final int temp = i;
            new Thread(() -> book.read(String.valueOf(temp))
                    , String.valueOf(i)).start();
        }

    }
}

class books {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //存
    public void write(String key, Object value) {
        readWriteLock.writeLock().lock();//开启写锁，保证每个开启的线程结束以后才去执行下一个线程
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + value);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    //取
    public void read(String key) {
        readWriteLock.readLock().lock();//开启读锁
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();//关闭读锁
        }

    }
}
