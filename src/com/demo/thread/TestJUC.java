package com.demo.thread;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * P21-CopyOnWriteArrayList
 * 测试JUC下安全类型的集合
 */
public class TestJUC {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            });
        }
        Thread.sleep(3000);
        System.out.println("-->" + list.size());
    }
}
