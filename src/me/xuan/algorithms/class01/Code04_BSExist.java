package me.xuan.algorithms.class01;

import me.xuan.algorithms.TestGenerate;

import java.util.Arrays;

public class Code04_BSExist {

    public static boolean bsExist(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = L + (R - L) / 2;
            if (num < arr[mid]) {
                R = mid - 1;
            } else if (num > arr[mid]) {
                L = mid + 1;
            } else {
                return true;
            }
        }
        return arr[L] == num;
    }

    public static void main(String[] args) {

        int[] arr = TestGenerate.generateRandomArray(100, 100);
        Arrays.sort(arr);


        for (int a : arr) {
            System.out.print(a + ", ");
        }
        System.out.println();

        System.out.println("-------测试结果");
        System.out.println(bsExist(arr, 5));

        System.out.println("-------- 对数器描述");
        System.out.println(Arrays.binarySearch(arr, 5) > 0);


    }

}
