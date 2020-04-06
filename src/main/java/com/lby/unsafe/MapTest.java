package com.lby.unsafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 并发修改异常:java.util.ConcurrentModificationException
 * @author macro
 * @Created on 2020-04-03
 */
public class MapTest {
    public static void main(String[] args) {
        //Map<Object, Object> map = new HashMap<>();
        //解决方案1
        //Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());
        Map<Object, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,4));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
