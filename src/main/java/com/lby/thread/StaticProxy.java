package com.lby.thread;

public class StaticProxy {
    public static void main(String[] args) {
        new Thread( ()-> System.out.println("dsads")).start();
        //通过代理对象方
        new Proxy(new MarryImpl()).happyMarry();
    }
}

//结婚接口
interface IMarry {
    void happyMarry();
}

//结婚实现类，实现结婚接口
class MarryImpl implements IMarry {

    public void happyMarry() {
        System.out.println("结婚啦");
    }
}

//代理类，实现结婚接口
class Proxy implements IMarry {
    //代理对象
    private IMarry target;

    //构造方法注入需要代理对象
    public Proxy(IMarry target) {
        this.target = target;
    }

    //婚庆公司准备婚庆
    public void happyMarry() {
        before();
        this.target.happyMarry();
        after();
    }

    private void after() {
        System.out.println("结婚以后收钱");
    }

    private void before() {
        System.out.println("结婚前布置现场");
    }
}
