package com.demo.search;

import java.util.ArrayList;

//二分查找前提为数组有序
public class BinarySearch {

    public static void main(String[] args) {

        int arr[] = {1, 8, 10, 89,89, 1000, 1234};

/*        int[] arr = new int[100];

        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }*/

        ArrayList<Integer> resIndex = binarySearch(arr, 0, arr.length-1, 1);

        System.out.println("resIndex=" + resIndex);

    }

    /**
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标 没找到就返回-1
     */
    public static ArrayList<Integer> binarySearch(int[] arr, int left, int right, int findVal) {

        //当left > right时 递归整个数组 没找到
        System.out.println("binarySearch");

        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            //思路
            //1.找到mid索引值 先不返回
            //2.向mid索引值左边扫描 将所有满足1000 的元素下标 加入到集合ArrayList
            //3.向mid索引值右边扫描 将所有满足1000 的元素下标 加入到集合Arraylist
            //4.将ArrayList返回

            ArrayList<Integer> resIndexlist = new ArrayList<>();
            //向mid 索引值左边扫描 将所有满足1000 的元素下标 加入到ArrayList
            int temp = mid - 1;

                while (true) {
                    if (temp < 0 || arr[temp] != findVal) {
                        //退出
                        break;
                    } else {
                        //
                        resIndexlist.add(temp);
                        temp -= 1;//左移
                    }
                }

                resIndexlist.add(mid);

                // 向mid索引值右边扫描 将所有满足1000 的元素下标 加入到集合Arraylist
                temp = mid + 1;

                while (true) {
                    if (temp > arr.length - 1 || arr[temp] != findVal) {
                        //退出
                        break;
                    } else {
                        //
                        resIndexlist.add(temp);
                        temp += 1;//右移
                    }

                }
//            return mid;
            return resIndexlist;
        }

    }
}