package com.lby.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author macro
 * @Created on 2020-04-03
 */
public class CallableTest {
    public static void main(String[] args) {
        /**
         * new Thread(new Runnable()).start();
         * new Thread(new FutureTask(Runnable)).start();
         * new Thread(new FutureTask(Callabe)).start();//适配器
         *
         */
        MyThread thread=new MyThread();
        FutureTask futureTask = new FutureTask<>(thread);
        //两个对象call会打印几次。打印一次。存在缓存
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();
        Object o = null;
        try {
            o = futureTask.get();//获取返回值
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(o);//这个可能会产生堵塞！把它放到最后
    }

}
class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}
