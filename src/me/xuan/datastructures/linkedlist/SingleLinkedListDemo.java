package me.xuan.datastructures.linkedlist;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        HeroNode hero5 = new HeroNode(3, "吴用1", "智多星1");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero2);

        //添加节点
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);

        singleLinkedList.list();

        System.out.println("链表修改后~~");
        //修改节点
        singleLinkedList.update(hero5);
        singleLinkedList.list();

        System.out.println("删除节点后~~");
        singleLinkedList.delete(hero1);
        singleLinkedList.list();

        singleLinkedList.delete(hero4);
        singleLinkedList.delete(hero2);
        singleLinkedList.delete(hero5);
        singleLinkedList.list();
    }
}

//定义SingleLinkedList
class SingleLinkedList {
    //先初始化一个头节点,头节点不要动
    private HeroNode head = new HeroNode(0, "", "");


    //添加节点到单向列表
    //当不考虑编号的顺序时,
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next 指向新的节点
    public void add(HeroNode heroNode) {

        HeroNode temp = head;
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
    }

    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
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
        temp.next = heroNode;
    }

    /**
     * 修改节点信息,根据no编号来修改,即no编号不能改
     *
     * @param heroNode
     */
    public void update(HeroNode heroNode) {

        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        HeroNode temp = head.next;
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

    public void delete(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号 %d 的节点,不能删除\n", heroNode.no);
        }
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        //因为头节点不能动,
        HeroNode temp = head.next;

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
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int hNo, String hName, String hNickName) {
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