package com.demo.thread;

/**
 * 多线程详解-P11停止线程
 */
public class TestStop implements Runnable{

    private boolean flag = true;

    @Override
    public void run(){
        int i = 0;
        while(flag){
            System.out.println("Thread runing:" + i++);
            // 电脑速度太快了导致main到900了t1还继续走下去，所以t1睡眠一下
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop t1 = new TestStop();
        new Thread(t1).start();
        for (int i = 0; i <= 1000; i++) {
            System.out.println("main:" + i);
            if (i == 900){
                t1.stop();
                System.out.println("线程停止了");
            }
        }
    }

}

