package com.demo.thread;

/**
 * 多线程详解
 * P15 监测线程状态
 */
public class TestState{

    public static void main(String[] args) {
        Thread thread = new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("===============");
        });

        // 监测线程状态 NEW
        Thread.State state = thread.getState();
        System.out.println(state);

        // 观察启动后 NEW
        thread.start();
        state = thread.getState();
        System.out.println(state);
        while (state != Thread.State.TERMINATED){
            try {
                Thread.sleep(100);
                state = thread.getState();
                System.out.println(state);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
