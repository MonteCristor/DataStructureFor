package com.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BbbleSort {
    public static void main(String[] args) {

//        int arr[] = {1,2,3,4,5};
//        int arr[] = {3,9,-1,10,20};

        //测试冒泡排序的速度 O(n^2) 测试80000条数据
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成[0,8000000)数
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String dateStr1 = sdf.format(date1);
        System.out.println("排序前的时间是：" + dateStr1);
        //测试冒泡排序
        bubbleSort(arr);
        Date date2 = new Date();
        String dateStr2 = sdf.format(date2);
        System.out.println("排序前的时间是：" + dateStr2);

    }


    //将冒泡排序封装为方法
    public static void  bubbleSort(int [] arr) {
        int temp = 0; //临时变量
        boolean flag = false; //标识是否经过排序
        for (int j = 0; j < arr.length -1; j++) {

            for (int i = 0; i < arr.length - j - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    flag = true;
                    temp = arr[i];
                    arr[i] = arr[i +1];
                    arr[i + 1] = temp;
                }
            }

            if (flag == false) { //在依次排序中 依次交换都没有发生过
                break;
            } else {
                flag = false; //重置flag 进行下次判断
            }
        }
    }
}
