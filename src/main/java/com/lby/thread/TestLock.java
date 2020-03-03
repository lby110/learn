package com.lby.thread;

import java.util.concurrent.locks.ReentrantLock;

//测试lock锁
public class TestLock  {
    public static void main(String[] args) {
        TestLock2 lock2=new TestLock2();
        lock2.start();
    }

}
class TestLock2 extends Thread{
    int nums=10;
    private final ReentrantLock lock=new ReentrantLock();
    @Override
    public void run() {
        while (true){
            try{
                lock.lock();
                System.out.println(nums--);
                if (nums==0){
                    break;
                }
            }finally {
                lock.unlock();
            }

        }
    }
}