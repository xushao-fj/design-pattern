package com.xsm.design.pattern.bridge;

/**
 * @author xsm
 * @Date 2020/7/11 21:36
 */
public class BridgeTest {
    public static void main(String[] args) {
        Implementor impl = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(impl);
        abs.Operation();
    }
}

/** 实现化角色*/
interface Implementor {
    void OperationImpl();
}

/** 具体实现化角色*/
class ConcreteImplementorA implements Implementor {

    @Override
    public void OperationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问");
    }
}

/** 抽象化角色*/
abstract class Abstraction {

    protected Implementor impl;

    public Abstraction(Implementor impl) {
        this.impl = impl;
    }

    public abstract void Operation();
}

/** 扩展抽象化角色*/
class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor impl) {
        super(impl);
    }

    @Override
    public void Operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
        impl.OperationImpl();
    }
}


