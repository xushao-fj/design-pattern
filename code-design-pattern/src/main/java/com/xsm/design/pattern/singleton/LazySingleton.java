package com.xsm.design.pattern.singleton;

/**
 * @author xsm
 * @date 2020/07/08
 * @description 饿汉式单例 DCL双重锁模式
 */
public class LazySingleton {

    /** 定义单例对象, 类加载并不作初始化操作*/
    private static volatile LazySingleton instance = null;

    /** 私有化构造器, 防止 new 对象*/
    private LazySingleton(){}

    public static LazySingleton getInstance(){
        if (instance == null) {
            // 加锁
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
