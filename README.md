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

