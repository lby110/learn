package com.lby.thread;

public class TestLambda2 {
    public static void main(String[] args) {
        Lambda result = (a,b) -> a+b;
        System.out.println(result.a(1,2));
    }
}

interface Lambda {
    int a(int a, int b);
    //double b(double a,double b);

}

class LambdaImpl implements Lambda {
    @Override
    public int a(int a, int b) {
        return a + b;
    }
}