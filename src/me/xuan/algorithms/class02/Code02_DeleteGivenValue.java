package me.xuan.algorithms.class02;

public class Code02_DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node removeValue(Node head, int num) {

        //delete all head equals num
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }

        Node prev = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }

            cur = cur.next;
        }

        return head;
    }

}
