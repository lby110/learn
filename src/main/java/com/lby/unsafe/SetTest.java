package com.lby.unsafe;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Set集合线程不安全,并发修改异常：Exception in thread "0" java.util.ConcurrentModificationException
 *
 * @author macro
 * @Created on 2020-04-03
 */
public class SetTest {
    public static void main(String[] args) {
        /**
         * 解决方法
         * 1.Set<String> set= Collections.synchronizedSet(new HashSet<>());//使用Collections工具类
         * 2.Set<String> set=new ConcurrentSkipListSet<>();
         * CopyOnWrite 写入时复制 COW 是计算机程序设计领域的一种优化策略。
         *多个线程调用list的时候 读取是固定的。写入（可能会导致覆盖）
         * 在写入的时候避免覆盖，造成数据丢失
         */
        Set<String> set=new HashSet<>();
        //Set<String> set = new ConcurrentSkipListSet<>();
        //Set<String> set= Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString());
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
