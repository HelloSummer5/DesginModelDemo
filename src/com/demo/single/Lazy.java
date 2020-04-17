package com.demo.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 懒汉式
 */
public class Lazy {

    private volatile static Lazy INSTACE;

    private static boolean flag = false;

    private Lazy(){
        /**
         * 3.三重锁,flag实际加密。
         * 缺点：再6的加密都有人解密
        */
        synchronized (Lazy.class) {
            // 4.为避免反射再次绕过单例
            if (!flag) {
                flag = true;
            } else {
               throw new RuntimeException("不要妄想搞事情");
            }

            // 3.为避免反射绕过单例
//            if (INSTACE != null) {
//                throw new RuntimeException("不要妄想搞事情");
//            }
        }
        System.out.println(Thread.currentThread().getName() + "-->ok");
    }

    /**
     * 多线程下有问题
     * @return
     */
    public static Lazy getInstance1(){
        if (null == INSTACE) {
            INSTACE = new Lazy();
        }
        return INSTACE;
    }

    /**
     * 双重检测锁模式，简称DCL懒汉式
     * 缺点：1.new对象是非原子操作，会发生指令重排
     *      2.反射可以获取对象
     * @return
     */
    public static Lazy getInstance2(){
        if (null == INSTACE) {
            synchronized (Lazy.class) {
                if (null == INSTACE) {
                    INSTACE = new Lazy(); // 非原子操作
                    /** 极端情况下
                     * 1.分配内存空间
                     * 2.调用构造方法，初始化对象
                     * 3.对象指向空间
                     * 期望执行    123
                     * 实际可能执行132(计算机允许执行3再执行2)
                     * A执行132没问题，但A走到3时突然切换县城B，B再进来时，此时A还没完成2没执行构造初始化，
                     * 但是B认为null!=INSTANCE，会直接返回没有构造完成的对象，
                     * 因此INSTACE对象需要添加volatile避免指令重排
                     */
                }
            }
        }
        return INSTACE;
    }

    public static void main(String[] args) throws Exception {
        // 1.多线程测试
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                Lazy.getInstance2();
//            }).start();
//        }

        // 3.反射绕过单例
//        Lazy instance = Lazy.getInstance2();
//        Constructor<Lazy> declaredConstructed = Lazy.class.getDeclaredConstructor(null);
//        declaredConstructed.setAccessible(true);
//        Lazy instance2 = declaredConstructed.newInstance();
//        System.out.println(instance);
//        System.out.println(instance2);

        // 4.反射再次绕过单例
//        Constructor<Lazy> declaredConstructed = Lazy.class.getDeclaredConstructor(null);
//        declaredConstructed.setAccessible(true);
//        Lazy instance = declaredConstructed.newInstance();
//        Lazy instance2 = declaredConstructed.newInstance();
//        System.out.println(instance);
//        System.out.println(instance2);

        // 5.破坏三重锁
        Field flag = Lazy.class.getDeclaredField("flag");
        flag.setAccessible(true);
        Constructor<Lazy> declaredConstructed = Lazy.class.getDeclaredConstructor(null);
        declaredConstructed.setAccessible(true);
        Lazy instance = declaredConstructed.newInstance();
        // 把第一个对象flag值改为false
        flag.set(instance, false);
        Lazy instance2 = declaredConstructed.newInstance();
        System.out.println(instance);
        System.out.println(instance2);
    }



}
