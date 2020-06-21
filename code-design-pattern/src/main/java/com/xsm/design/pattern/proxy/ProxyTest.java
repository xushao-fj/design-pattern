package com.xsm.design.pattern.proxy;

/**
 * @author xsm
 * @Date 2020/6/21 14:24
 */
public class ProxyTest {

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }

}

/**
 * 抽象主题
 */
interface Subject {

    void request();
}

/**
 * 真实主题
 */
class RealSubject implements Subject{

    @Override
    public void request() {
        System.out.println("访问真实主题的方法...");
    }
}

/**
 * 代理
 */
class Proxy implements Subject {

    private RealSubject realSubject;

    @Override
    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.request();
        postRequest();
    }

    private void postRequest() {
        System.out.println("访问真实主题之后的后续处理...");
    }

    private void preRequest() {
        System.out.println("访问真实主题之前的预处理...");
    }
}
