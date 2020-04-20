package com.demo.thread;

/**
 * 多线程详解-P19
 * 抢票例子
 */
public class TestTicket implements Runnable{

    private int ticketNum = 10;

    private boolean flag = true;

    public static void main(String[] args) {
        TestTicket ticket = new TestTicket();
        new Thread(ticket, "苦逼的我").start();
        new Thread(ticket, "厉害的你").start();
        new Thread(ticket, "可恶的黄牛").start();
    }

    @Override
    public void run() {
        while (flag) {
            buy();
        }
    }

    public synchronized void buy(){
        if (ticketNum <= 0){
            flag = false;
            System.out.println("Over.");
            return;
        }
        // 模拟延时, 根据自己电脑调时间
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "拿了第" + ticketNum-- + "票");
    }
}

