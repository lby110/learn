## 1多线程
### 1.1继承Thread父类（不推荐使用，避免单继承的局限性）
- 步骤<br>
    1.继承Thread<br>
    2.重写Run方法<br>
    3.调动start方法启动多线程
    
### 多线程下载图片Demo
```java
//继承Thread
public class TestThread extends Thread {
    private String url;
    private String name;

    public TestThread(String url,String name){
        this.url=url;
        this.name=name;
    }
    //重写run方法
    @Override
    public void run() {
        webDownloader webDownloader=new webDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件名："+name);
    }

    //主线程
    public static void main(String[] args) {
        TestThread t1=new TestThread("https://dss0.baidu.com/73t1bjeh1BF3odCf/it/u=422137604,301522349&fm=85&s=C5973F9E8031718A0049D95E030050F3","1.png");
        TestThread t2=new TestThread("https://dss0.baidu.com/73t1bjeh1BF3odCf/it/u=422137604,301522349&fm=85&s=C5973F9E8031718A0049D95E030050F3","2.png");
        TestThread t3=new TestThread("https://dss0.baidu.com/73t1bjeh1BF3odCf/it/u=422137604,301522349&fm=85&s=C5973F9E8031718A0049D95E030050F3","3.png");
        t1.start();
        t2.start();
        t3.start();
    }
}
//下载图片的方法
class webDownloader{
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("io异常，downloader方法出现异常");
        }
    }
}
```
### 1.2实现Runnable接口（推荐使用，灵活方便，同一个对象可以被）
- 步骤<br>
    1.实现Runnable<br>
    2.重写Run方法<br>
    3.在主线程中实例化对象<br>
    4.实例化Thread并把3中实例化的对象传进去并开启start方法
```java
public class TestThread2 implements Runnable {
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("多线程学习"+i);
        }
    }

    public static void main(String[] args) {
        //1.实例化对象
        TestThread2 testThread2=new TestThread2();
        //2.实例化thread对象并把1的实例化对象传进去调用start方法
        new Thread(testThread2).start();
        for (int i = 0; i < 20; i++) {
            System.out.println("来学习"+i);
        }
    }

}
```
## 2.初识多线程并发问题--模拟抢票

```java
public class TestThread3 implements Runnable{
    private int ticketNums=10;
    public void run() {
        //票数
        while (true){
            if (ticketNums<=0){
                break;
            }
            try {
                //模拟延时
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNums--+"票");
        }
    }

    public static void main(String[] args) {
        TestThread3 t1=new TestThread3();
        new Thread(t1,"小明").start();
        new Thread(t1,"小李").start();
        new Thread(t1,"黄牛").start();
    }
}
```
结果：发现会有人重复拿到同一张票---思考如何避免下面这种情况
```text
小李拿到了第9票
小明拿到了第10票
黄牛拿到了第10票
黄牛拿到了第8票
小李拿到了第7票
小明拿到了第7票
小李拿到了第6票
小明拿到了第6票
黄牛拿到了第5票
小李拿到了第3票
黄牛拿到了第3票
小明拿到了第4票
小李拿到了第2票
黄牛拿到了第2票
小明拿到了第2票
黄牛拿到了第0票
小李拿到了第1票
小明拿到了第0票
```
## 3.案例（龟兔赛跑）
1.赛道距离（相当于for循环），然后离终点越来越近  
2.判断比赛是否结束  
3.打印胜利者  
4.龟兔赛跑开始  
5.故事中乌龟赢，兔子输。兔子中途要睡觉（相当于让线程sleep）  
6.最终乌龟获胜
```java
public class Race implements Runnable {
    //胜利者常量
    private static String winnder;
    //模拟龟兔赛跑
    public void run() {
        for (int i = 0; i <= 100; i++) {
            //模拟兔子睡觉
            if (Thread.currentThread().getName().equals("兔子")){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //调用方法判断游戏是否结束
            boolean flag=this.gameOver(i);
            if (flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"跑了"+i+"步");
        }
    }
    //判断游戏是否结束方法
    public boolean gameOver(int steps) {
        if (winnder!=null){
            return true;
        }{
            if (steps>=100){
                winnder=Thread.currentThread().getName();
                System.out.println("winnder is:"+winnder);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race=new Race();
        new Thread(race,"兔子").start();
        new Thread(race,"乌龟").start();
    }
}
```

## 4.静态代理
静态代理对象小结：1.真实对象和代理对象都需要实现同一个接口  
2.代理对象需要代理真实对象
3.代理对象可以做很多真实对象无法完成的事情
4.真实对象可以专注自己的事情
```java
public class StaticProxy {
    public static void main(String[] args) {
        //通过代理对象方
        Proxy proxy=new Proxy(new MarryImpl());
        proxy.happyMarry();
    }
}

//结婚接口
interface IMarry {
    void happyMarry();
}
//结婚实现类，实现结婚接口.真实角色结婚
class MarryImpl implements IMarry {

    public void happyMarry() {
        System.out.println("结婚啦");
    }
}

//代理角色，帮忙结婚
class Proxy implements IMarry {
    //代理对象，代理真实角色
    private IMarry target;

    //构造方法注入需要代理对象
    public Proxy(IMarry target) {
        this.target = target;
    }

    //婚庆公司准备婚庆
    public void happyMarry() {
        before();
        this.target.happyMarry();//这就是真实对象
        after();
    }
    private void after() {
        System.out.println("结婚以后收钱");
    }

    private void before() {
        System.out.println("结婚前布置现场");
    }
}

```
## 5.Lambda表达式
- 避免匿名内部类定义过多
- 实质为函数式编程
- 更加简洁  
- 去掉无用代码、留下核心逻辑

```text
函数式接口的定义：
    1.任何接口，如果只包含一个抽象方法，那么它就是一个函数式接口
    public interface Runnable{
        public abstract void run();
    }
    2.对于函数式接口，可以通过lambda表达式来创建该接口的对象

```
```java

/**
 * 推导lambda表达式
 */
public class TestLambda {
    //3.改为静态内部类
    static class Like2Impl implements ILike {
        @Override
        public void lambda() {
            System.out.println("学习lambda表达式2");
        }
    }

    public static void main(String[] args) {

        ILike like = new LikeImpl();
        like.lambda();

        like = new Like2Impl();
        like.lambda();

        //4.由静态内部类改为局部内部类
        class Like3Impl implements ILike {
            @Override
            public void lambda() {
                System.out.println("学习lambda表达式3");
            }
        }
        like = new Like3Impl();
        like.lambda();

        //5.匿名内部类;没有类的名称，只能借助接口或者父类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("学习lambda表达式4");
            }
        };
        like.lambda();

        //6.改为lambda表达式
        like = () -> {
            System.out.println("学习lambda表达式5");
        };
        like.lambda();

    }
}

//1.定义一个函数式接口
interface ILike {
    void lambda();
}

//2.接口实现类
class LikeImpl implements ILike {

    @Override
    public void lambda() {
        System.out.println("学习lambda表达式");
    }
}

```
Lambda练习
```java

public class TestLambda2 {
    public static void main(String[] args) {
        Lambda result = (int a, int b) -> a + b;
        System.out.println(result.a(1,2));
    }
}

interface Lambda {
    int a(int a, int b);

}

class LambdaImpl implements Lambda {
    @Override
    public int a(int a, int b) {
        return a + b;
    }

}
```

## 6.线程的状态
5个状态：创建线程（线程启动后）->就绪状态（获取CPU资源后）->运行状态（释放CPU资源后变为就绪状态）  
->阻塞状态 ->（执行完毕后）死亡状态

```text
    1.新生状态（Thread thread =new Thread();//线程对象一旦创建就进入到新生这状态）
    2.就绪状态（thread.start();//立即进入到就绪状态。但不意味着能够立即调度）
    3.运行状态（CPU调度以后进入运行状态，线程才真正执行线程体里面的代码块）
    4.阻塞状态（如果调用了sleep、wait或者同步锁定时，线程进入到阻塞状态，代码不往下执行。阻塞状态解除以后，重新改为就绪状态，  
    等待CPU调度执行。）  
    5.dead死亡状态（线程一旦中断或结束，进入到死亡状态。就不能再启动了）
    
```

### 7.线程停止
- 一般不会使用自带的stop方法。官方不建议使用
- 推荐建议使用一个标志位进行终止变量。
```java

public class TestThread4 implements Runnable {
    
    //标志位
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run...Thread" + i++);
        }
    }

    public void stop(){
        this.flag=false;
    }

    public static void main(String[] args) {
        TestThread4 thread4=new TestThread4();
        new Thread(thread4).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("main"+i);
            if (i==900){
                //调用自己的stop方法将标志位改为false，将线程停止
                thread4.stop();
                System.out.println("线程停止");
            }
        }
    }
}

```
## 8.线程休眠（sleep）
- sleep指定当前线程阻塞的毫秒数；
- sleep存在InterruptedException异常；
- sleep时间达到后线程进入就绪状态
- sleep可以模拟网络延迟、倒计时
- 每一个对象都有一个锁，sleep不会释放锁

模拟倒计时
```java
public class TestSleep {
    public static void test() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);//睡眠1s
            System.out.println(num--);
            if (num<=0){
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        test();
    }
}
```

## 9.线程礼让（yield）
- 礼让线程，让当前正在执行的线程暂停，但不阻塞
- 礼让线程让在运行状态的线程转为就绪状态
- 礼让线程并不是真正意义上的礼让，而是将在运行状态的线程重新转为就绪状态供CPU随机调用
- A线程为运行状态，B为就绪状态。A线程调用礼让方法。A转为就绪状态和B同时竞争CPU。
  最后执行哪个线程是CPU随机选择的。（礼让并不一定成功）
```java

public class TestYield implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始执行");
        //Thread.yield();//礼让方法
        System.out.println(Thread.currentThread().getName()+"结束执行");
    }

    public static void main(String[] args) {
        TestYield testYield=new TestYield();
        new Thread(testYield,"a").start();
        new Thread(testYield,"b").start();
    }
}

未开启礼让方法执行结果：
a开始执行
a结束执行
b开始执行
b结束执行

开启礼让方法执行结果：
（礼让失败结果）
a开始执行
b开始执行
a结束执行
b结束执行

（礼让成功结果）
b开始执行
a开始执行
b结束执行
a结束执行
```

## 10.守护（daemon）线程
- 线程分为用户线程、守护线程
- 虚拟机必须确保用户线程执行完毕
- 虚拟机不用等待守护线程执行完毕

## 11.线程不安全案例（上文的抢票也是线程不安全的）
```java
public class TestUnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "基金");//创建账户
        Drawing boy = new Drawing(account, 50, "boy");
        Drawing girl = new Drawing(account, 100, "girl");
        boy.start();
        girl.start();
    }
}

//账户
class Account {
    int money;//余额
    String IDCard;//卡号

    public Account(int money, String IDCard) {
        this.money = money;
        this.IDCard = IDCard;
    }
}
//创建交易
class Drawing extends Thread {
    Account account;//账户
    int drawingMoney;//取了多少钱
    int nowMoney;//现在手头上的钱

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {

        if (account.money - drawingMoney < 0) {
            System.out.println(Thread.currentThread().getName() + "余额不足");
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //卡内余额=余额-取出的钱
        account.money = account.money - drawingMoney;
        //手里的钱
        nowMoney += drawingMoney;
        System.out.println(account.IDCard + "余额为" + account.money);
        System.out.println(this.getName() + "手里的钱为" + nowMoney);
    }
}
```

## 12.synchronized
- synchronized在方法上，锁的是this（该对象）
```java
package com.lby.thread;

public class TestUnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "基金");
        Drawing boy = new Drawing(account, 50, "boy");
        Drawing girl = new Drawing(account, 100, "girl");
        boy.start();
        girl.start();
    }
}

//账户
class Account {
    int money;//余额
    String IDCard;//卡号

    public Account(int money, String IDCard) {
        this.money = money;
        this.IDCard = IDCard;
    }
}

class Drawing extends Thread {
    Account account;//账户
    int drawingMoney;//取了多少钱
    int nowMoney;//现在手头上的钱

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //添加synchronized，默认锁的是this
    @Override
    public synchronized void run() {

        if (account.money - drawingMoney < 0) {
            System.out.println(Thread.currentThread().getName() + "余额不足");
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //卡内余额=余额-取出的钱
        account.money = account.money - drawingMoney;
        //手里的钱
        nowMoney += drawingMoney;
        System.out.println(account.IDCard + "余额为" + account.money);
        System.out.println(this.getName() + "手里的钱为" + nowMoney);
    }
}

执行结果：发现还是有负数存在，说明上面方法有问题.则使用代码块
基金余额为-50
基金余额为-50
boy手里的钱为50
girl手里的钱为100
```
- synchronized(obj){}  
Obj:称之为监视器，但是一般推荐使用共享资源作为同步监视器
```java
package com.lby.thread;

public class TestUnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "基金");
        Drawing boy = new Drawing(account, 50, "boy");
        Drawing girl = new Drawing(account, 100, "girl");
        boy.start();
        girl.start();
    }
}

//账户
class Account {
    int money;//余额
    String IDCard;//卡号

    public Account(int money, String IDCard) {
        this.money = money;
        this.IDCard = IDCard;
    }
}

class Drawing extends Thread {
    Account account;//账户
    int drawingMoney;//取了多少钱
    int nowMoney;//现在手头上的钱

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        //哪里增删改了锁哪个。锁的对象就是变化的量
        synchronized (account) {
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "余额不足");
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //卡内余额=余额-取出的钱
            account.money = account.money - drawingMoney;
            //手里的钱
            nowMoney += drawingMoney;
            System.out.println(account.IDCard + "余额为" + account.money);
            System.out.println(this.getName() + "手里的钱为" + nowMoney);
        }
    }
}
```
### 13.死锁
- 某一个同步代码块同时拥有两个以上对象的锁，就可能发生死锁
#### 14.产生死锁的四个必要条件
1.互斥条件：一个资源只能被一个进程使用  
2.请求与保持条件：一个进程因请求资源被阻塞时，对已经获得资源保持不放  
3.不剥夺条件：进程已经获得的资源，在未使用完之前，不能强行剥夺  
4.循环等待条件，若干进程之间形成一种头尾相接的循环等待资源关系

```java
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
        //死锁代码块
        if (choice==0){
            synchronized (lipstick){
                System.out.println(this.getName()+"获得口红的锁");
                Thread.sleep(1000);
                synchronized (mirror){
                    System.out.println(this.getName()+"获得镜子的锁");
                }
            }
        }else {
            synchronized (mirror){
                System.out.println(this.getName()+"获得镜子的锁");
                Thread.sleep(1000);
                synchronized (lipstick){
                    System.out.println(this.getName()+"获得口红的锁");
                }
            }
        }
    }
    
    /**替换makeup方法可解决死锁
    * public void makeUp() throws InterruptedException {
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
    * /
*/
}
```

### 15.Lock锁（性能更好）

```java
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
    //定义lock锁
    private final ReentrantLock lock=new ReentrantLock();
    @Override
    public void run() {
        while (true){
            try{
                lock.lock();//lock枷锁
                System.out.println(nums--);
                if (nums==0){
                    break;
                }
            }finally {
                lock.unlock();//lock解锁
            }

        }
    }
}
```