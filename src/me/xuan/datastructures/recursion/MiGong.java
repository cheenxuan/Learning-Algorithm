package me.xuan.datastructures.recursion;

public class MiGong {

    public static void main(String[] args) {
        //先创建一个二位数组,模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙,不能通过
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;
//        map[2][2] = 1;
//        map[1][2] = 1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }

        System.out.println("-----------------------------------");
        //使用递归回溯给小球找路
        setWay(map, 1, 1);

        //输出新的地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }

    //说明
    // 1.map表示地图
    //2.i,j表示从地图的哪个位置开始出发(1,1)
    //3.如果小球能到map[6][5]则说明通路找到
    //4.约定当map[i][j]为0表示该点没有走过 当为1表示墙  2表示通路可以走  3.表示该店已经走过,但是走不通
    //5.再走迷宫时,需要确定一个策略(方法) 下->右->上->左 ,如果该点走不通再回溯
    //使用递归回溯来给小球找路
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没有走过
                //按照策略走
                map[i][j] = 2;//假定该点是可以走通的

                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }

    }
}
