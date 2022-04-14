package me.xuan.datastructures.linkedlist;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        System.out.println("双向链表的测试~~");

        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(23, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(32, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(12, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);

        doubleLinkedList.list();

//        System.out.println("修改后~~");
//        HeroNode2 hero5 = new HeroNode2(12, "林冲1", "豹子头1");
//        doubleLinkedList.update(hero5);
//        doubleLinkedList.list();
//
//        System.out.println("删除!!!");
//        doubleLinkedList.delete(32);
//
//        doubleLinkedList.list();
    }
}

class DoubleLinkedList{

    //先初始化一个头节点,头节点不要动
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    public void add(HeroNode2 heroNode) {

        HeroNode2 temp = head;
        //遍历链表
        while (true) {
            //当temp.next == null 表示找到链表的最后一个节点
            if (temp.next == null) {
                break;
            }

            //如果没有找到
            temp = temp.next;
        }

        //当退出while循环时.temp就指向了链表的最后
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        boolean flag = false;//表示 hero添加的编号是否存在,默认为false

        while (true) {
            if (temp.next == null) {
                break;
            }

            if (temp.next.no > heroNode.no) {
                break;
            }

            if (temp.next.no == heroNode.no) {//说明此编号已经存在,不能再添加
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag) {
            System.out.printf("准备的插入的hero的编号 %d 已经存在,不能加入\n", heroNode.no);
            return;
        }

        heroNode.next = temp.next;
        heroNode.pre = temp;
        temp.next = heroNode;
        if (heroNode.next != null) {
            heroNode.next.pre = heroNode;
        }
    }

    public void update(HeroNode2 heroNode) {

        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }

            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.printf("没有找到编号 %d 的节点,不能修改\n", heroNode.no);
        }
    }

    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到编号 %d 的节点,不能删除\n", no);
        }
    }

    //遍历双向链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        //因为头节点不能动,
        HeroNode2 temp = head.next;

        while (true) {
            //判断是否到最后了
            if (temp == null) {
                break;
            }
            //打印数据
            System.out.println(temp);
            //后移
            temp = temp.next;
        }
    }
}


//定义一个Node 每个Node对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int hNo, String hName, String hNickName) {
        this.no = hNo;
        this.name = hName;
        this.nickName = hNickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}


