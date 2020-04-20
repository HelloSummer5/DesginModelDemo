package com.demo.thread;

/**
 * P21-死锁
 * 多个线程相互抱着对方的资源，相互形成僵持
 *
 * 产生死锁的四个必要条件：
 * 1.互斥条件：一个资源每次只能被一个进程使用
 * 2.请求与保持条件：一个进程因请求资源而阻塞时，对已获得对资源保持不放
 * 3.不剥夺条件：进程已获得的资源，在未使用完之前，不能强行剥夺
 * 4.循环等待条件：若干进程之间形成一种头尾相接的循环等待资源关系
 */
public class DeadLock {

    public static void main(String[] args) {
        Makeup g1 = new Makeup(0, "灰菇凉");
        Makeup g2 = new Makeup(1, "白雪公主");
        g1.start();
        g2.start();
    }
}

/**
 * 口红
 */
class Lipstick{

}

/**
 * 镜子
 */
class Mirror{

}

class Makeup extends Thread{
    // 需要的资源只有一份，用static来表示一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;
    String girlName;

    Makeup(int choice, String girlName){
        this.choice = choice;
        this.girlName = girlName;
    }

    /**
     * 化妆
     */
    @Override
    public void run() {
        try {
            makup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void makup() throws InterruptedException {
        if(choice == 0){
            synchronized (lipstick) {
                System.out.println(this.girlName + "获得口红的锁");
                Thread.sleep(1000);
                synchronized (mirror){
                    System.out.println(this.girlName + "获得c的锁");
                }
            }
        }else {
            synchronized (mirror) {
                System.out.println(this.girlName + "获得镜子的锁");
                Thread.sleep(2000);
                synchronized (lipstick){
                    System.out.println(this.girlName + "获得口红的锁");
                }
            }
        }
    }
}
