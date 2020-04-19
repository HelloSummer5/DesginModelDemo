package com.demo.thread;

/**
 * 多线程详解-P19
 * 造成线程不安全的原因是每个线程自己工作内存取到的余额和主内存不一样，存在可见性问题
 */
public class UnSafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "结婚基金");
        Drawing d1 = new Drawing(account, 50, "小明");
        Drawing d2 = new Drawing(account, 100, "小明女友");
        d1.start();
        d2.start();
    }
}

/**
 * 账户类
 */
class Account{
    /** 余额 **/
    int balance;
    /** 卡名 **/
    String name;
    public Account(int balance, String name){
        this.balance = balance;
        this.name = name;
    }

}

/** 银行:模拟取款 **/
class Drawing extends Thread{
    /** 余额 **/
    private Account account;
    /** 取了多少钱 **/
    private int drawMoney;
    /** 现在手里有多少钱 **/
    private int nowMoney;

    public Drawing(Account account, int drawMoney, String name){
        this.account = account;
        this.drawMoney = drawMoney;
        super.setName(name);
    }

    @Override
    public void run() {

        if (account.balance - drawMoney < 0){
            System.out.println(this.getName() + "的余额不足");
            return;
        }
        // 模拟延时
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.name  + "余额为" + account.balance);

        // 最新余额 = 账户余额 - 取的钱
        account.balance -= drawMoney;
        // 你手里的钱 = 现有的钱 + 取的钱
        nowMoney += drawMoney;
        System.out.println(account.name  + "余额为" + account.balance);
        System.out.println(this.getName() + "手里的钱为：" + nowMoney);
    }
}



