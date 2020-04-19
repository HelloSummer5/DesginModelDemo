package com.demo.lamda;

/**
 * 带参数lamda原理
 * 总结：
 * 1.只有函数式接口才可以简化成lamda
 * 2.接口实现方法中只有一行代码才能简化
 * 3.多个参数也可以去掉参数类型，要去掉都去掉参数类型，但是必须加上括号
 * 目的：引申Runnable，Runnable也是函数式接口，可以用lamda表达式简化
 *
 * PS:[玉藻前]别问，问就是阴阳师。
 */
public class TestLamda2 {

    public static void main(String[] args) {
        // 常规匿名内部类调用
        ILove love = new ILove(){
            @Override
            public void love(String things) {
                System.out.println("[匿名内部类调用] I love " + things);
            }
        };
        love.love("玉藻前");

        // lamda表达式调用
        love = (String things) -> {
            System.out.println("[lamda表达式调用调用] I love " + things);
        };
        love.love("玉藻前");

        // 简化1 去掉参数类型
        love = (things) -> {
            System.out.println("I love " + things);
        };
        love.love("摄影1");

        // 简化2 去掉括号
        love = things -> {
            System.out.println("I love " + things);
        };
        love.love("摄影2");

        // 简化2 去掉花括号
        love = things -> System.out.println("I love " + things);
        love.love("摄影2");

        // 总结3.多个参数也可以去掉参数类型，要去掉都去掉参数类型，但是必须加上括号
        IWonder wonder = (a, b) -> System.out.println("I wonder " + a + ", I wonder " + b);
        wonder.wonder("how", "why");

        // Runnable简化
        Runnable runnable = () -> System.out.println("多线程开启快乐时光～～");
        runnable.run();
    }
}

/**
 * 这里把教程中参数类型改了下
 */
interface ILove{
    void love(String things);
}

interface IWonder{
    void wonder(String how, String why);
}
