package me.xuan.datastructures.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(23, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(32, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(12, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(62, "林冲", "豹子头");


        HeroNode hero11 = new HeroNode(12, "宋江", "及时雨");
        HeroNode hero21 = new HeroNode(52, "卢俊义", "玉麒麟");
        HeroNode hero31 = new HeroNode(33, "吴用", "智多星");
        HeroNode hero41 = new HeroNode(51, "林冲", "豹子头");

//        HeroNode hero5 = new HeroNode(3, "吴用1", "智多星1");
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
        singleLinkedList.addByOrder(hero5);

        singleLinkedList.list();

        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByOrder(hero21);
        singleLinkedList1.addByOrder(hero11);
        singleLinkedList1.addByOrder(hero41);
        singleLinkedList1.addByOrder(hero31);

        singleLinkedList1.list();

//        System.out.println("链表修改后~~");
//        //修改节点
//        singleLinkedList.update(hero5);
//        singleLinkedList.list();

//        System.out.println("删除节点后~~");
//        singleLinkedList.delete(hero1);
//        singleLinkedList.list();
//
//        singleLinkedList.delete(hero4);
//        singleLinkedList.delete(hero2);
//        singleLinkedList.delete(hero5);
//        singleLinkedList.list();

//        int length = getLength(singleLinkedList.getHead());
//        System.out.printf("该链表的有效节点数为: %d \n", length);
//
//        System.out.printf("找到倒数第 %d 节点 heroNode = " + findLastIndexNode(singleLinkedList.getHead(), 4) + "\n", 1);
//
//        HeroNode reverse = reverseLinkedList(singleLinkedList.getHead());
//
//        list(reverse);
//        System.out.println("测试逆序打印单链表~");
//        reversePrint(singleLinkedList.getHead());

        HeroNode head = mergeLinkedList(singleLinkedList.getHead(), singleLinkedList1.getHead());
        list(head);
    }

    public static HeroNode mergeLinkedList(HeroNode head1, HeroNode head2) {
        if (head1.next == null) {
            return head2;
        }
        if (head2.next == null) {
            return head1;
        }
        SingleLinkedList newSingleLinkedList = new SingleLinkedList();
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        HeroNode temp = null;
        while (cur1 != null || cur2 != null) {
            if (cur1 == null) {
                temp = cur2;
                cur2 = cur2.next;
                temp.next = null;
                newSingleLinkedList.add(temp);
                break;
            }
            if (cur2 == null) {
                temp = cur1;
                cur1 = cur1.next;
                temp.next = null;
                newSingleLinkedList.add(temp);
                break;
            }

            if (cur2.no < cur1.no) {
                temp = cur2;
                cur2 = cur2.next;
            } else if (cur1.no < cur2.no) {
                temp = cur1;
                cur1 = cur1.next;
            } else {
                temp = cur1;
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            temp.next = null;
            newSingleLinkedList.add(temp);
        }

        return newSingleLinkedList.getHead();
    }

    //从尾到头打印单链表
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }

        //创建一个栈,将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //单链表的反转
    //1.先定义一个临时头节点 reverseHead
    //2.遍历源链表 并逐一添加到reverseHead的最前端
    //3.让head.next 指向reverseHead.next
    public static HeroNode reverseLinkedList(HeroNode head) {
        //判断链表是否为空
        if (head.next == null || head.next.next == null) {
            return head;
        }
        //1.先定义一个临时头节点 reverseHead
        HeroNode reverseHead = new HeroNode(100, "", "");
        HeroNode cur = head.next;
        while (cur != null) {
            //2.遍历源链表 并逐一添加到reverseHead的最前端
            HeroNode next = cur.next;

            cur.next = reverseHead.next;
            reverseHead.next = cur;

            cur = next;
        }
        //3.让head.next 指向reverseHead.next
        head.next = reverseHead.next;
        return head;
    }

    //查找单链表中的倒数第K个节点
    //思路:
    //1.编写一个方法接收head节点,同事接收一个index
    //2.index表示是倒数第index个节点
    //3.先把链表从头到尾遍历,得到俩女校的总的长度getLength
    //4.得到size后,我们从链表的第一个开始遍历(size-index)个,就可以得到
    //5.
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return null;
        }

        int size = getLength(head);

        if (index <= 0 || index > size) {
            return null;
        }

        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //显示链表
    public static void list(HeroNode head) {
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

    //方法:获取到单链表的节点的个数(如果是带头结点的链表,需求不统计头结点)

    /**
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }

        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        return length;
    }
}

//定义SingleLinkedList
class SingleLinkedList {
    //先初始化一个头节点,头节点不要动
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

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