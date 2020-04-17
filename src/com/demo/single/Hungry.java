package com.demo.single;

/**
 * 饿汉式
 */
public class Hungry {

    private static Hungry INSTANCE = new Hungry();

    private Hungry(){

    }

    public Hungry getInstance(){
        return INSTANCE;
    }
}
