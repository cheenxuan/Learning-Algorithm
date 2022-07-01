package me.xuan.datastructures.recursion;

public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义一个array,保存皇后放置位置的结果
    int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {

        Queue8 queue8 = new Queue8();
        queue8.check(0);

        System.out.printf("一共有%d种解法",count);
    }

    //放置第n个皇后
    private void check(int n) {
        if (n == max) {//n=8
            print();
            return;
        }

        //依次放入皇后并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n放在该行的第一列
            array[n] = i;
            //判断当放置第n个皇后到i列时,是否冲突
            if (judge(n)) {
                //接着放n+1个皇后
                check(n + 1);
            }
        }
    }

    //查看当我们放置第N个皇后时,就去检测该皇后是否和前面已经摆放的皇后冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
