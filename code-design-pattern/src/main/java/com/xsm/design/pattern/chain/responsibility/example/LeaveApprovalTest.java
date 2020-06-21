package com.xsm.design.pattern.chain.responsibility.example;

/**
 * @author xsm
 * @Date 2020/6/21 15:46
 * 责任链 案例: 用责任链模式设计一个请假条审批模块
 * 分析：假如规定学生请假小于或等于 2 天，班主任可以批准；小于或等于 7 天，系主任可以批准；小于或等于 10 天，院长可以批准；其他情况不予批准；
 *      这个实例适合使用职责链模式实现。
 *
 * 首先，定义一个领导类（Leader），它是抽象处理者，包含了一个指向下一位领导的指针 next 和一个处理假条的抽象处理方法 handleRequest(int LeaveDays)；
 * 然后，定义班主任类（ClassAdviser）、系主任类（DepartmentHead）和院长类（Dean），它们是抽象处理者的子类，是具体处理者，
 * 必须根据自己的权力去实现父类的 handleRequest(int LeaveDays) 方法，如果无权处理就将假条交给下一位具体处理者，直到最后；
 * 客户类负责创建处理链，并将假条交给链头的具体处理者（班主任）。
 */
public class LeaveApprovalTest {

    public static void main(String[] args) {
        // 组装责任链
        Leader teacher1 = new ClassAdviser();
        Leader teacher2 = new DepartmentHead();
        Leader teacher3 = new Dean();
        // Leader teacher4=new DeanOfStudies();
        teacher1.setNext(teacher2);
        teacher2.setNext(teacher3);
        // teacher3.setNext(teacher4);
        // 提交请求
        teacher1.handleRequest(8);
    }
}

/** 抽象处理者: 领导类*/
abstract class Leader {

    private Leader next;

    public Leader getNext() {
        return next;
    }

    public void setNext(Leader next) {
        this.next = next;
    }

    /**
     * 处理请求的方法
     * @param leaveDays
     */
    public abstract void handleRequest(int leaveDays);
}

/** 处理类: 班主任*/
class ClassAdviser extends Leader {

    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 2) {
            System.out.println("班主任批准你请假" + leaveDays + "天");
        }
        else {
            if (getNext() != null) {
                getNext().handleRequest(leaveDays);
            }
            else {
                System.out.println("请假天数太多, 没有批准该假条!");
            }
        }
    }
}

/** 处理类: 系主任*/
class DepartmentHead extends Leader{

    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 7) {
            System.out.println("系主任批准你请假" + leaveDays + "天");
        }
        else {
            if (getNext() != null) {
                getNext().handleRequest(leaveDays);
            }
            else {
                System.out.println("请假天数太多, 没有批准该假条!");
            }
        }
    }
}

/** 处理类: 院长*/
class Dean extends Leader {

    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 10) {
            System.out.println("院长批准你请假" + leaveDays + "天");
        }
        else {
            if (getNext() != null) {
                getNext().handleRequest(leaveDays);
            }
            else {
                System.out.println("请假天数太多, 没有批准该假条!");
            }
        }
    }
}

/** 处理类: 教务处长类*/
class DeanOfStudies extends Leader {

    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 20) {
            System.out.println("教务处长类批准你请假" + leaveDays + "天");
        }
        else {
            if (getNext() != null) {
                getNext().handleRequest(leaveDays);
            }
            else {
                System.out.println("请假天数太多, 没有批准该假条!");
            }
        }
    }
}



