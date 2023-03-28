package me.xuan.algorithms.class01;

import me.xuan.algorithms.TestGenerate;

public class Code03_InsertionSort {

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j+1]; j--) {
                    swap(arr, j, j + 1);
            }

            for (int a : arr) {
                System.out.print(a + ", ");
            }
            System.out.println();
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {

        int[] arr = TestGenerate.generateRandomArray(10, 10);
        System.out.println("输入数据-----------");
        for (int a : arr) {
            System.out.print(a + ", ");
        }
        System.out.println();
        System.out.println("开始排序-----------");
        insertionSort(arr);

        System.out.println("排序完成-----------");
        for (int a : arr) {
            System.out.print(a + ", ");
        }
    }
}
