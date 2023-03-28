package me.xuan.algorithms.class01;

import me.xuan.algorithms.TestGenerate;

import java.util.Arrays;

public class Code05_BSNearLeft {


    public static int bsNearLeft(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int index = -1;

        while (L <= R) {
            mid = L + (R - L) / 2;
            if (arr[mid] < num) {
                L = mid + 1;
            } else {
                index = mid;
                R = mid - 1;
            }
        }

        return index;
    }

    public static int bsNearRight(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (arr[mid] <= num) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        int[] arr = TestGenerate.generateRandomArray(50, 100);
        Arrays.sort(arr);

        for (int a : arr) {
            System.out.print(a + ", ");
        }

        System.out.println();
        System.out.println("测试开始");
        int index = bsNearLeft(arr, 6);
        System.out.println("index = " + index + ",value = " + arr[index]);

        int index2 = bsNearRight(arr, 6);
        System.out.println("index = " + index2 + ",value = " + arr[index2]);


    }


//    /**
//     * 在arr上,找到满足>=value的最左位置
//     * @param arr
//     * @param value
//     * @return
//     */
//    public static int nearestIndex(int[] arr, int value) {
//        int L = 0;
//        int R = arr.length - 1;
//        int index = -1;
//        while (L <= R) {
//            int mid = L + ((R - L) >> 1);
//            if (arr[mid] >= value) {
//                index = mid;
//                R = mid - 1;
//            } else {
//                L = mid + 1;
//            }
//        }
//        return index;
//    }
}
