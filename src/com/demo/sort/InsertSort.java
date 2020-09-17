package com.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSort {

    public static void main(String[] args) {

     /*   int[] arr = {101,34,119,1,-1,89};
        insertSort(arr);*/
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成[0,8000000)数
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String dateStr1 = sdf.format(date1);
        System.out.println("排序前的时间是：" + dateStr1);

        insertSort(arr);

        Date date2 = new Date();
        String dateStr2 = sdf.format(date2);
        System.out.println("排序前的时间是：" + dateStr2);

    }

    //插入排序
    public static void insertSort(int[] arr){

        //
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;

            //给insertVal 找到插入的位置
            //保证 给insertVal找插入位置时 不越界
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                //insertVal < arr[insertIndex] 说明待插入的数还没有找到插入的位置
                //将arr[insertIndex]后移
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex -- ;
            }
            //当退出while循环是说明插入位置找到 insertIndex + 1


            if (insertIndex + 1 == i) {
                arr[insertIndex + 1] = insertVal;

            }

//            System.out.println("第"+i+"轮插入");
//            System.out.println(Arrays.toString(arr));
        }

/*        //推导
        //定义
        int insertVal = arr[1];
        int insertIndex = 1 - 1;

        //给insertVal 找到插入的位置
        //保证 给insertVal找插入位置时 不越界
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            //insertVal < arr[insertIndex] 说明待插入的数还没有找到插入的位置
            //将arr[insertIndex]后移
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex -- ;
        }
        //当退出while循环是说明插入位置找到 insertIndex + 1

        arr[insertIndex + 1] = insertVal;

        System.out.println("第一轮");*/
    }
}
