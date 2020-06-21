
# 设计模式
### 代理模式 
#### 1. 代理模式的定义
代理模式的定义: 由于某些原因需要给某对象提供一个代理以控制对该对象的访问. 这时, 访问对象不适合或者不能直接引用目标对象, 代理对象作为访问对象和目标之间的中介.

代理模式的主要优点有: 
- 代理模式在客户端与目标对象之间起到一个中介作用和保护目标对象的作用
- 代理对象可以扩展目标对象的功能
- 代理模式能将客户端与目标对象分离, 在一定程度上降低了系统的耦合度

主要缺点:  
- 在客户端和目标对象之间增加了一个代理对象, 会造成请求处理速度变慢
- 增加了系统的复杂度  
#### 2. 代理模式的结构与实现  
- 1 抽象主题类(Subject): 通过接口或抽象类声明真实主题和代理对象实现的业务方法
- 2 真实主题类(Real Subject): 实现了抽象主题中的具体业务, 是代理对象所代表的真实对象, 是最终引用的对象
- 3 代理类(Proxy): 提供了与真实主题相同的接口, 其内部含有对真实主题的引用, 它可以访问, 控制或扩展真实主题的功能  

![代理模式结构图](./doc/design-pattern-picture/proxy.png)  

#### 3. 代理模式的应用场景  
- 远程代理: 为了隐藏目标对象存在于不同地址空间的事实, 方便客户端访问. ex: 用户申请某些网盘空间时, 会在用户的文件系统中建立一个虚拟的硬盘, 用户访问虚拟硬盘时实际访问的是网盘空间.
- 延迟加载: 为了提高系统的性能, 延迟对目标的加载. ex: `Hibernate`中就存在属性的延迟加载和关联表的延时加载.
- 安全代理: 用于控制不同种类客户对于真实对象的访问权限  
- Spring中的应用: `AOP`动态代理

--- 
### 责任链模式(职责链)
#### 1. 责任链定义与特点
定义:  
为了避免请求发送者与多个请求处理者耦合在一起,将所有请求的处理者通过前一对象记住其下一个对象的引用而连成一条链;当有请求发生时, 可将请求沿着这条链传递, 直到有对象处理它为止.  
在责任链模式中, 客户只需要将请求发送到责任链上即可, 无须关心请求的处理细节和请求的传递过程, 所以责任链将请求的方法者和处理者解耦了.
  
优点: 
- 1 降低了对象之间的耦合度. 该模式是的一个对象无须知道是哪一个对象处理其请求以及链的结构, 发送者和接收者也无须拥有对方的明确信息.  
- 2 增强了系统的可扩展性. 可以根据需要增加新的请求处理类, 满足开闭原则.  
- 3 增强了给对象指派职责的灵活性. 当工作流程发生变化, 可以动态地改变链内的成员或者调动他们的次序, 也可以动态地新增或者删除责任. 
- 4 简化了对象之间的链接. 每个对象只需要保持一个指向其后继者的引用, 不需保持其他所有处理者的引用, 这避免了使用众多的if或者if..else语句.  
- 5 责任分担, 每个类只需要处理自己该处理的工作,不该处理的传递给下一个对象完成,明确各类的责任范围, 符合类的单一职责原则.  

缺点: 
- 1 不能保证每个请求一定被处理. 由于一个请求没有明确的接收者, 所以不能保证它一定会被处理, 该请求可能一直传到链的末端都得不到处理.
- 2 对比较长的职责链, 请求的处理可能涉及多个处理对象, 系统性能将受到一定影响.
- 3 职责链简历的合理性要靠客户端来保证, 增加了客户端的复杂性,可能会由于职责链的错误设置而导致系统出错.ex: 可能会造成循环调用.

#### 2. 模式的结构与实现  
- 模式的结构  
>- 抽象处理者(Handler): 定义一个处理请求的接口, 包含抽象处理方法和一个后继连接.  
>- 具体处理者(Concrete Handler): 实现抽象处理者的处理方法, 判断能否处理本次请求, 如果可以处理请求则处理, 否则将该请求转给它的后继者.  
![责任链结构图](./doc/design-pattern-picture/责任链1.png)  
![责任链结构图](./doc/design-pattern-picture/责任链图2.png) 
- 模式的实现  
```java
package com.xsm.design.pattern.chain.responsibility;

/**
 * @author xsm
 * @Date 2020/6/21 15:16
 * 责任链模式
 */
public class ChainOfResponsibilityPattern {

    public static void main(String[] args) {
        // 组装责任链
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        // 提交请求
        handler1.handleRequest("two");
    }

}

/**
 * 抽象处理者角色
 */
abstract class Handler {
    private Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }

    /**
     * 处理请求的方法
     *
     * @param request
     */
    public abstract void handleRequest(String request);
}

/**
 * 具体处理者角色1
 */
class ConcreteHandler1 extends Handler {

    @Override
    public void handleRequest(String request) {
        if ("one".equals(request)) {
            System.out.println("具体处理这1负责处理该请求!");
        }
        else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            }
            else {
                System.out.println("没有人处理该请求!");
            }
        }
    }
}

/** 具体处理者角色2*/
class ConcreteHandler2 extends Handler {

    @Override
    public void handleRequest(String request) {
        if ("two".equals(request)) {
            System.out.println("具体处理者2负责处理该请求！");
        }
        else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            }
            else {
                System.out.println("没有人处理该请求！");
            }
        }
    }
}

```   
- 模式模拟案例 
`com.xsm.design.pattern.chain.responsibility.example.LeaveApprovalTest`

- 模式的应用场景 
>- 1 有多个对象可以处理一个请求, 哪个对象处理该请求由运行时刻自动确定. 
>- 2 可动态指定一组对象处理请求, 或添加新的处理者.  
>- 在不明确指定请求处理者的情况下, 向多个处理者中的一个提交请求.
 
  


 
  



