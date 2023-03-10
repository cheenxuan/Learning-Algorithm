package me.xuan.algorithms;

public class Code07_EvenTimesOddTimes {

    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        System.out.println(eor);
    }

    /**
     * arr中,有两种数,出现奇数次
     *
     * @param arr
     */
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        int rightOne = eor & (~eor + 1);
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }

        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    public static int bit1Counts(int N) {
        int count = 0;
        while (N != 0) {
            int rightOne = N & ((~N) + 1);
            count++;
            N ^= rightOne;
        }

        return count;
    }
}


