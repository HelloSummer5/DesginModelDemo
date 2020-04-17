package com.demo.thread;

/**
 * 多线程详解-P12线程休眠
 * 用途1：模拟网络延时：放大问题的发生性
 */
public class TestSleep implements Runnable {

    private int tiketNum = 10;

    @Override
    public void run() {
        while (true) {
            if (tiketNum <= 0) {
                break;
            }
            // 模拟延时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->拿到了第" + tiketNum-- + "张票");
        }
    }

    public static void main(String[] args) {
        TestSleep t1 = new TestSleep();
        new Thread(t1, "小明").start();
        new Thread(t1, "老师").start();
        new Thread(t1, "黄牛").start();
    }
}
