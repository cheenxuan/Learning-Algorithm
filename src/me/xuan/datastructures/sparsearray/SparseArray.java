package me.xuan.datastructures.sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始数据的二维数组 11*11
        //0: 表示没有棋子  1: 表示黑子  2: 表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][4] = 2;
        //输出原始的二维数组
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        int sum = 0;

        for (int row = 0; row < chessArr1.length; row++) {
            for (int col = 0; col < chessArr1[row].length; col++) {
                if (chessArr1[row][col] != 0) {
                    sum++;
                }
            }
        }

        System.out.printf("sum = %d\n", sum);


        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        System.out.println("-------------------------------------------");
        int i = 1;
        for (int row = 0; row < chessArr1.length; row++) {
            for (int col = 0; col < chessArr1[row].length; col++) {
                if (chessArr1[row][col] != 0) {
                    sparseArr[i][0] = row;
                    sparseArr[i][1] = col;
                    sparseArr[i][2] = chessArr1[row][col];
                    i++;
                }
            }
        }

        for (int j = 0; j < sparseArr.length; j++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[j][0], sparseArr[j][1], sparseArr[j][2]);
        }

        System.out.println("-----------------  保存数据  --------------------------");
        //保存数据
        try {
            File writePath = new File("file/");
            File writeName = new File("file/map.data");
            if (!writePath.exists()) {
                writePath.mkdirs();
            }
            if (!writeName.exists()) {
                writeName.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(writeName);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);

            for (int j = 0; j < sparseArr.length; j++) {
                bw.write(sparseArr[j][0] + "," + sparseArr[j][1] + "," + sparseArr[j][2]);
                bw.newLine();
            }
            bw.flush();
            bw.close();
            osw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("----------------  读取数据  ---------------------------");
        //读取数据
        try {
            File writeName = new File("file/map.data");
            if (writeName.exists()) {
                FileReader fr = new FileReader(writeName);
                BufferedReader br = new BufferedReader(fr);

                String readText = null;
                List<String[]> list = new ArrayList<>();
                while ((readText = br.readLine()) != null) {
                    String[] split = readText.split(",");
                    list.add(split);
                }

                int[][] sparseArr2 = new int[list.size()][3];
                for (int j = 0; j < list.size(); j++) {
                    sparseArr2[j][0] = Integer.parseInt(list.get(j)[0]);
                    sparseArr2[j][1] = Integer.parseInt(list.get(j)[1]);
                    sparseArr2[j][2] = Integer.parseInt(list.get(j)[2]);
                }

                int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
                for (int j = 1; j < sparseArr.length; j++) {
                    chessArr2[sparseArr2[j][0]][sparseArr2[j][1]] = sparseArr2[j][2];
                }

                //输出原始的二维数组
                for (int[] row : chessArr2) {
                    for (int data : row) {
                        System.out.printf("%d\t", data);
                    }
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
