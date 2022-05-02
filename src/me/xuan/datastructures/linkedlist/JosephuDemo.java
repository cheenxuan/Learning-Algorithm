package me.xuan.datastructures.linkedlist;

public class JosephuDemo {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(25);
        circleSingleLinkedList.showBoy();

        //测试小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 25);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    private Boy first = new Boy(-1);

    //添加节点
    public void addBoy(int nums) {

        if (nums < 1) {
            System.out.println("nums的值不正确~");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {

            //根据编号创建节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空,");
            return;
        }

        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }

            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入,计算出小孩出圈的顺序
    public void countBoy(int startNo, int count, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误,请重新输入");
            return;
        }

        //创建一个辅助指针
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        for (int i = 1; i < startNo; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (true) {
            if (helper == first) {
                break;
            }

            for (int i = 1; i < count; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            System.out.printf("小孩出圈编号 %d \n", first.getNo());

            first = first.getNext();
            helper.setNext(first);
        }

        System.out.printf("最后小孩的编号 %d \n", first.getNo());
        first = null;
        helper = null;
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
