package com.lby.thread;

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

        //6.lambda
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
