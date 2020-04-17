package com.demo.thread;

public class ThreadTest1 implements Runnable {

    private static String winner;
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (Thread.currentThread().getName().equals("兔子") && i%10==0){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 判断比赛是否结束
            boolean flag = gameOver(i);
            if (flag){
                break;
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + i + "步");
        }
    }

     private boolean gameOver(int step){
        if (winner != null){
            // 已经存在冠军
            return true;
        }else if (step >= 100){
            winner = Thread.currentThread().getName();
            System.out.println(winner + "是冠军");
            return true;
        }
        return false;
     }

    public static void main(String[] args) {
        ThreadTest1 race = new ThreadTest1();
        new Thread(race, "乌龟").start();
        new Thread(race, "兔子").start();
    }
}
