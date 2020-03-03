package com.lby.thread;


//测试守护线程
public class TestDaemon  {

}
//上帝
class God implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("上帝保佑着你");
        }
    }
}

//我
class you implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <36500 ; i++) {
            System.out.println("一生快快乐乐");
        }
        System.out.println("goodbye，World");
    }
}
