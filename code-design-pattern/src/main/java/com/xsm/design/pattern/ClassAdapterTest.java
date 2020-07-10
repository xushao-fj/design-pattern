package com.xsm.design.pattern;

/**
 * @author xsm
 * @date 2020/07/10
 * @description
 */
/** 目标接口*/
interface Target {

    /**
     * 目标接口方法
     */
    void request();
}

/** 适配者*/
class Adaptee {
    public void specificRequest(){
        System.out.println("适配者中的业务代码被调用!");
    }
}

/** 类适配器*/
class ClassAdapter extends Adaptee implements Target {

    @Override
    public void request() {
        specificRequest();
    }
}

/** 客户端*/
public class ClassAdapterTest {

    public static void main(String[] args) {
        System.out.println("类适配器模式测试");
        Target target = new ClassAdapter();
        target.request();
    }

}
