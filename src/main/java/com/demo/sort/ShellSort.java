package com.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

    public static void main(String[] args) {

        /*int [] arr = {8,9,1,7,2,3,5,4,6,0};*/

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成[0,8000000)数
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String dateStr1 = sdf.format(date1);
        System.out.println("排序前的时间是：" + dateStr1);

        shellSort(arr);

//        System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String dateStr2 = sdf.format(date2);
        System.out.println("排序前的时间是：" + dateStr2);


    }

/*    public static void shellSort(int [] arr) {
        //根据逐步分析 使用循环处理
        int count = 1;
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            int temp = 0;
            for (int i = gap; i< arr.length; i++) {
                //共gap组 每组2个元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素 交换
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
            *//*System.out.println("希尔排序第"+(count++)+"轮后:");
            System.out.println(Arrays.toString(arr));*//*
        }

*//*        int temp = 0;
        //第一轮
        //第一轮排序是将10个数据分为了5组
        for (int i = 5; i< arr.length; i++) {
            //共五组 每组2个元素
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素 交换
                if (arr[j] > arr[j + 5]){
                    temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }

            }
        }
        System.out.println("希尔排序第一轮后:");
        System.out.println(Arrays.toString(arr));


        //第2轮
        //第2轮排序是将10个数据分为了2组
        for (int i = 2; i< arr.length; i++) {
            //共五组 每组2个元素
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素 交换
                if (arr[j] > arr[j + 2]){
                    temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }

            }
        }
        System.out.println("希尔排序第2轮后:");
        System.out.println(Arrays.toString(arr));


        //第3轮
        //第3轮排序是将10个数据分为了1组
        for (int i = 1; i< arr.length; i++) {
            //共五组 每组2个元素
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于加上步长后的那个元素 交换
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }

            }
        }
        System.out.println("希尔排序第3轮后:");
        System.out.println(Arrays.toString(arr));*//*

    }*/

    //缩小增量排序
    //对交换式的希尔排序进行优化  位移法
    public static void shellSort(int[] arr) {

        //增量gap 逐步缩小增量
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            //从第gap个元素开始逐个对其所在的组进行直接插入
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j-gap]){
                    while(j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //当退出while循环后 找到temp插入的位置
                    arr[j] = temp;
                }

            }

        }

    }


}
