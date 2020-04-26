package com.demo.thread;

/**
 * P24、P25生产消费者问题-管程法
 * 利用缓冲区解决
 */
public class TestPC {

    public static void main(String[] args) {
        SysContainer container = new SysContainer();
        new Productor(container).start();
        new Consumer(container).start();
    }

}

/**
 * 生产者
 */
class Productor extends Thread{

    SysContainer container;

    public Productor(SysContainer container){
        this.container = container;
    }
    
    public void run(){
        for (int i = 0; i < 100; i++) {
            container.push(new Chicken(i));
            System.out.println("生产了第" + i + "只鸡");
          }
    }

}

/**
 * 消费者
 */
class Consumer extends Thread{

    SysContainer container;

    public Consumer(SysContainer container){
        this.container = container;
    }

    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了第--->" + container.pop().id + "号鸡");
        }
    }

}

/**
 * 产品
 */
class Chicken{
    int id; // 产品编号
    public Chicken(int id) {
        this.id = id;
    }
}

/**
 * 缓冲区
 */
class SysContainer{
    /**
     * 容器大小
     */
    Chicken[] chickens = new Chicken[100];

    /**
     * 产品计数器
     */
    int count = 0;

    /**
     * 生产者放入产品
     * 通知消费者消费，生产等待
     */
    public synchronized void push(Chicken chicken){
        // 如果容器满了，就需要等待消费者消费
        if (count == chickens.length){
            // 通知生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 如果没有满，丢入产品
        chickens[count] = chicken;
        count++;

        // 可以通知消费者消费了
        this.notifyAll();

    }

    /**
     * 消费者消费产品
     */
    public synchronized Chicken pop(){
        // 判断能否消费
        if (count == 0){
            // 等到生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 如果可以消费
        count--;
        Chicken chicken = chickens[count];

        // 吃完了，通知生产者生产
        this.notifyAll();
        return chicken;
    }
}
