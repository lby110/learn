package com.lby.thread;

//死锁：多个线程互相抱着对方需要的资源，然后形成僵持
public class DeadLock {
    public static void main(String[] args) {
        Makeup makeup=new Makeup(0,"小美");
        Makeup makeup2=new Makeup(1,"小李");
        makeup.start();
        makeup2.start();
    }
}
//口红
class Lipstick{

}
//镜子
class Mirror{

}

//化妆
class Makeup extends Thread{
    //需要的资源只有一份，所以用static来保证资源的唯一性
    static Lipstick lipstick=new Lipstick();
    static Mirror mirror=new Mirror();
    //选择
    int choice;
    //化妆的人
    String girlName;
    public Makeup(int choice,String girlName){
        this.choice=choice;
        this.girlName=girlName;
    }

    @Override
    public void run() {
        try {
            makeUp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void makeUp() throws InterruptedException {
        if (choice==0){
            synchronized (lipstick){
                System.out.println(this.getName()+"获得口红的锁");
                Thread.sleep(1000);
            }
            synchronized (mirror){
                System.out.println(this.getName()+"获得镜子的锁");
            }
        }else {
            synchronized (mirror){
                System.out.println(this.getName()+"获得镜子的锁");
                Thread.sleep(1000);
            }
            synchronized (lipstick){
                System.out.println(this.getName()+"获得口红的锁");
            }

        }
    }
}