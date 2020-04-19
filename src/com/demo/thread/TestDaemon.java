package com.demo.thread;

public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();
        Thread t1 = new Thread(god);
        // 默认是false，用户线程
        t1.setDaemon(true);
        t1.start();
        //
        new Thread(you).start();
    }
}

class God implements Runnable{
    @Override
    public void run() {
        while (true) {
            System.out.println("上帝守护你");
        }
    }
}

class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 35600; i++) {
            System.out.println("你一生都开心地活着");
        }
        System.out.println("====Goodbye, word===");
    }
}