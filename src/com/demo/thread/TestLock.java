package com.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * P23-Lock
 * synchronized与Lock的对比
 * 1.Lock是显示锁（手动开启和关闭锁，别忘记关闭锁）；synchronized是隐式锁，出了作用域自动释放
 * 2.Lock只有代码块锁；synchronized有代码块锁和方法锁
 * 3.使用Lock锁，JVM将话费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提供更多的子类）
 * 4.优先使用顺序：
 *      Lock > 同步代码块（已经进入了方法体，分配了相应资源）> 同步方法（在方法体之外）
 */
public class TestLock {
    public static void main(String[] args) {
        TestLock2 TestLock2 = new TestLock2();
        new Thread(TestLock2).start();
        new Thread(TestLock2).start();
        new Thread(TestLock2).start();
    }
}

class TestLock2 implements Runnable{

    int ticketNum = 10;

    // 定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                if (ticketNum > 0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNum--);
                }else {
                    break;
                }
            }finally {
               lock.unlock();
            }
        }
    }
}
