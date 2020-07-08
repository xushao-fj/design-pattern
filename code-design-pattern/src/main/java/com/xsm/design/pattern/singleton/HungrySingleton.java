package com.xsm.design.pattern.singleton;

/**
 * @author xsm
 * @date 2020/07/08
 * @description 饿汉式单例, 类一旦加载就创建一个单例
 */
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return instance;
    }

}
