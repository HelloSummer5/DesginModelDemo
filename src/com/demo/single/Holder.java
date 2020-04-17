package com.demo.single;

/**
 * 静态内部类
 */
public class Holder {

    private Holder(){

    }

    public Holder getInstance(){
        return InnerHolder.INSTANCE;
    }

    public static class InnerHolder{
        private final static Holder INSTANCE = new Holder();
    }
}
