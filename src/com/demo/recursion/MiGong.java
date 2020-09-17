package com.demo.recursion;

public class MiGong {

    public static void main(String[] args) {
        //创建二维数组 模拟迷宫
        int [][] map  = new int[8][7];
        //使用 1 表示墙 先上下置为1
        for (int i = 0; i < 7; i++) {
            map [0][i] = 1;
            map [7][i] = 1;
        }
        //左右置为1
        for (int i = 0; i < 8; i++) {
            map [i][0] = 1;
            map [i][6] = 1;
        }

        //设置挡板
        map [3][1] = 1;
        map [3][2] = 1;

        //输出地图
        //地图的情况
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯 找路
        setWay(map, 1, 1);

        //输出新地图 走过并标识过的图
        //标识过的地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯找路
    // 1.墙 2.通路可走 3.改点已走过
    //策略 下 右 上 左
    public static boolean setWay(int[][] map, int i, int j) {

        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                //当前的点还没走过 按策略走
                map[i][j] = 2;//假定该点可以走通
                if (setWay(map, i + 1 , j)){
                    //想下走
                    return true;
                } else if (setWay(map, i, j+1)){
                    return true;
                } else if (setWay(map, i-1, j)){
                    return true;
                } else  if (setWay(map, i,  j-1)){
                    //向左走
                    return true;
                } else {
                    //说明该点走不通 是死路
                    map[i][j] = 3;
                }
            } else { // 如果map[i][j] != 0 可能是 1 2 3
                return false;
            }
        }



        return false;
    }

}
