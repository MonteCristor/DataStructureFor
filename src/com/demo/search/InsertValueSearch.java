package com.demo.search;

public class InsertValueSearch {

    public static void main(String[] args) {

       /* int[] arr = new int[100];

        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }*/

        int arr[] = {1, 8, 10, 89,89, 1000, 1234};

        int index = insertValueSearch(arr, 0, arr.length - 1, 1);

        System.out.println(index);

    }

    //编写插值查找算法
    public static int insertValueSearch(int [] arr, int left, int right, int findValue){

        System.out.println("调用insertValueSearch");

        //findValue < arr[0] || findValue > arr[arr.length - 1] 条件必须要 否则得到的mid可能越界
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]){
            return -1;
        }

        //求出mid 自适应
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if (findValue > midValue){
            //向右递归
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            //向左递归
            return insertValueSearch(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }




    }
}
