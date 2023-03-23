package me.xuan.algorithms.class02;

public class Code04_RingArray {

    public static class MyQueue {
        public int[] arr;
        public int pushi;
        public int polli;
        public int size;
        public int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("栈满了,不能再加了");
            }

            size++;
            arr[pushi] = value;
            pushi = nextIndex(pushi);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("栈空了,不能再拿了");
            }

            size--;
            int res = arr[polli];
            polli = nextIndex(polli);
            return res;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }


}
