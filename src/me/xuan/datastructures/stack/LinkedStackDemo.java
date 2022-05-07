package me.xuan.datastructures.stack;

import java.util.Scanner;

public class LinkedStackDemo {
    public static void main(String[] args) {
        //测试ArrayStack
        //先创建一个arrayStack对象
        LinkedStack stack = new LinkedStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 表示退出程序");
            System.out.println("show: 表示显示栈");
            System.out.println("push: 表示添加数据到栈");
            System.out.println("pop: 表示从栈中取出数据");

            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int val = stack.pop();
                        System.out.printf("出栈的数据是: %d\n", val);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出了~");
    }
}

class LinkedStack {

    private int maxSize;
    private int count = -1;
    private Node head;

    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
        head = new Node(-1, null);
    }

    public boolean isFull() {
        return count == maxSize - 1;
    }

    public boolean isEmpty() {
        return head.next == null || count == -1;
    }

    public void push(int val) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }

        Node node = new Node(val, head.next);
        head.next = node;
        count++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }

        Node node = head.next;
        head.next = head.next.next;
        count--;
        return node.val;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        Node cur = head;
        int curIndex = -1;
        while (cur.next != null) {
            Node node = cur.next;
            curIndex++;
            System.out.printf("stack[%d] = %d\n", curIndex, node.val);
            cur = cur.next;
        }
    }

}

class Node {
    public int val;
    public Node next;

    public Node(int val, Node node) {
        this.val = val;
        this.next = node;
    }
}
