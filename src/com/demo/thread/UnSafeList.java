package com.demo.thread;

import java.util.ArrayList;
import java.util.List;

public class UnSafeList {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(500);
        System.out.println(list.size());
    }
}
