package com.lby.function;

import java.util.Arrays;
import java.util.List;

/**
 * @author macro
 * @Created on 2020-04-05
 * 题目要求：一分钟内完成此题，只能用一行代码实现
 *现在有以下五个用户！进行筛选：
 * 1-ID必须为偶数
 * 2-年龄必须大于23
 * 3-用户名转为大写
 * 4-用户名字母倒着排序
 * 5-只输出一个
 */
public class demo05 {
    public static void main(String[] args) {
        USer u1 = new USer(1, "a", 21);
        USer u2 = new USer(2, "b", 22);
        USer u3 = new USer(3, "c", 23);
        USer u4 = new USer(4, "d", 24);
        USer u5 = new USer(6, "e", 25);
        List<USer> uSers = Arrays.asList(u1, u2, u3, u4, u5);
        uSers.stream()
                .filter(u->{return u.getId()%2==0;})
                .filter(uSer -> {return uSer.getAge()>23;})
                .map(uSer -> {return uSer.getName().toUpperCase();})
                .sorted((uu1,uu2)->{return uu2.compareTo(uu1);})
                .limit(1)
                .forEach(System.out::println);
        ;

    }
}
