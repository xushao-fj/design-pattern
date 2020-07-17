package mediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xsm
 * @date 2020/07/17
 * @description 中介者模式
 */
public class MediatorPattern {

    public static void main(String[] args) {
        Mediator md = new ConcreteMediator();
        Colleague c1, c2;
        c1 = new ConcreteColleague1();
        c2 = new ConcreteColleague2();
        md.register(c1);
        md.register(c2);
        c1.send();
        System.out.println("-------------");
        c2.send();
    }

}

/**
 * 抽象中介者
 */
abstract class Mediator {

    /**
     * 注册
     */
    public abstract void register(Colleague colleague);

    /**
     * 转发
     *
     * @param colleague
     */
    public abstract void relay(Colleague colleague);
}

/**
 * 具体中介者
 */
class ConcreteMediator extends Mediator {

    private List<Colleague> colleagues = new ArrayList<>();

    @Override
    public void register(Colleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleagues.add(colleague);
            colleague.setMediator(this);
        }
    }

    @Override
    public void relay(Colleague colleague) {
        for (Colleague ob : colleagues) {
            if (!Objects.equals(ob, colleague)) {
                ob.receive();
            }
        }
    }
}

/**
 * 抽象同事类
 */
abstract class Colleague {

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    protected Mediator mediator;


    public abstract void receive();

    public abstract void send();
}

/**
 * 具体同事类
 */
class ConcreteColleague1 extends Colleague {

    @Override
    public void receive() {
        System.out.println("具体同事类1收到请求");
    }

    @Override
    public void send() {
        System.out.println("具体同事类1发出请求");
        // 请中介者转发
        mediator.relay(this);
    }
}

/**
 * 具体同事类
 */
class ConcreteColleague2 extends Colleague {

    @Override
    public void receive() {
        System.out.println("具体同事类2收到请求");
    }

    @Override
    public void send() {
        System.out.println("具体同事类2发出请求");
        // 请中介者转发
        mediator.relay(this);
    }
}