package com.demo.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 多线程详解-P12线程休眠
 * 用途2：倒计时
 * 记住：每个对象都有一把锁，sleep不会释放锁
 */
public class TestSleep2 {

    public static void main(String[] args) throws InterruptedException{
        // tenDown();
        //  printCurrentTime();
        int i = 10;
        System.out.println(i++ == 10? ++i : --i);
    }

    /**
     * 倒计时
     * PS：这里循环不同而已
     */
    public static void tenDown()throws InterruptedException{
        for (int i = 10; i >= 0; i--) {
            Thread.sleep(1000);
            System.out.println(i);
        }
    }

    /**
     * 打印当前时间
     */
    public static void printCurrentTime() throws InterruptedException{
        Date statTime = new Date(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            // 每隔一秒打印一次
            Thread.sleep(1000);
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(statTime));
            // 更新时间
            statTime = new Date(System.currentTimeMillis());
        }
    }
}
