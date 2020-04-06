package com.lby.unsafe;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * java.util.ConcurrentModificationException 并发修改异常
 * CopyOnWrite比Vector的优势点：Vector使用了synchronized效率比较低
 * @author macro
 * @Created on 2020-04-03
 */
public class ListTest {
    public static void main(String[] args) {
        //并发下ArrayList是不安全的，synchronized
        /**
         * 解决方案：
         * 1、List<String> list = new Vector<>();
         * 2、List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3、List<String> list = new CopyOnWriteArrayList<>();
         * CopyOnWrite 写入时复制 COW 是计算机程序设计领域的一种优化策略。
         *多个线程调用list的时候 读取是固定的。写入（可能会导致覆盖）
         * 在写入的时候避免覆盖，造成数据丢失
         */
        List<String> list = new CopyOnWriteArrayList<>();

        //List<String> list = new Vector<>();
        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
