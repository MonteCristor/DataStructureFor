package com.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {

    public static void main(String[] args) {

      /*  int[] arr = {101,34,119,1};
        selectSort(arr);*/

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成[0,8000000)数
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String dateStr1 = sdf.format(date1);
        System.out.println("排序前的时间是：" + dateStr1);
        selectSort(arr);
        Date date2 = new Date();
        String dateStr2 = sdf.format(date2);
        System.out.println("排序前的时间是：" + dateStr2);

    }

    //选择排序
    public static void selectSort(int[] arr) {

        //原始 101,34,119,1
        //第一轮 1,34,119， 101

        //第一轮
        //选择排序时间复杂度O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j =  i + 1; j < arr.length ; j++) {
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

        /*    System.out.println("第"+(i+1+"轮后~~"));
            System.out.println(Arrays.toString(arr));*/
        }

     /*   for (int j = 0 + 1; j < arr.length ; j++) {
            if (min > arr[j]){ //说明假定的最小值不是最小
                min = arr[j];  //重置min
                minIndex = j; //重置minIndex
            }

        }

        //将最小值 放在[0] 即交换
        arr[minIndex] = arr[0];
        arr[0] = min;

        System.out.println(min);
        System.out.println(Arrays.toString(arr));*/

    }
}
