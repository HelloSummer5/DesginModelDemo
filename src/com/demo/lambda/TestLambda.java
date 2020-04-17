package com.demo.lambda;

/**
 * lambda原理
 * 函数式编程接口-一个接口中只有一个方法
 */
public class TestLambda {
    static class Like2 implements ILike{
        @Override
        public void lamda() {
            System.out.println("I like lamda2.");
        }
    }
    public static void main(String[] args) {
        // 1.写一个实现类
        ILike like = new Like();
        like.lamda();

        // 2.静态内部类
        like = new Like2();
        like.lamda();

        // 3.内部类
        class Like3 implements ILike{
            @Override
            public void lamda() {
                System.out.println("I like lamda3.");
            }
        }
        like = new Like3();
        like.lamda();

        // 4.匿名内部类
        like = new ILike() {
            @Override
            public void lamda() {
                System.out.println("I like lamda4.");
            }
        };
        like.lamda();

        // 5.lamda表达式，省略了4中接口名方法名
        like = () -> {
            System.out.println("I like lamda5.");
        };
        like.lamda();
    }
}

interface ILike{
    void lamda();
}

/**
 * 1.写一个实现类
 */
class Like implements ILike{
    @Override
    public void lamda() {
        System.out.println("I like lambda.");
    }
}




