package com.demo.proxy;

public class StaticProxy {
    public static void main(String[] args) {
        new Thread(()-> System.out.println("loving you")).start();
        new MarryCompany(new You()).HappyMarry();
    }
}


interface Marry{
    void HappyMarry();
}

class You implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("XXX要结婚了");
    }
}

class MarryCompany implements Marry{

    private Marry target;

    public MarryCompany(Marry target){
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        target.HappyMarry();
        after();
    }

    void before(){
        System.out.println("酒席准备、司仪安排");
    }
    void after(){
        System.out.println("彩礼结算");
    }
}