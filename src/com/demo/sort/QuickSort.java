package com.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuickSort {

    public static void main(String[] args) {

        /*int [] arr = {-9, 78, 0, 23, -567, 70,-1,900,4561};*/

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 800000000); //生成[0,8000000)数
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String dateStr1 = sdf.format(date1);
        System.out.println("排序前的时间是：" + dateStr1);

        quickSort(arr,0, arr.length -1);

//        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String dateStr2 = sdf.format(date2);
        System.out.println("排序前的时间是：" + dateStr2);

    }

    public static void  quickSort(int[] arr, int left, int right) {

        int l = left;//左下标
        int r = right;//右下标

        //中轴
        int pivot = arr[(left + right) / 2];
        int temp = 0;//临时变量 作为交换时使用

        //while循环的目的是让比pivot值小的放到左边 比pivot大的值放到右边
        while (l < r) {
            //在pivot的左边一直找 找到一个大于等于pivot的值 才退出
            while ( arr[l] < pivot){
                l += 1;
            }
            //在pivot的右边一直找 找到一个小于等于pivot的值 才退出
            while( arr[r] > pivot) {
                r -= 1;
            }
            //如果 l >= r 说明pivot 的左右两的值 已经按照左边全部是小于等于pivot的值 右边全部是大于等于pivot的值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后 发现arr[l] == pivot值相等 -- 前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后 发现arr[r] == pivot值相等 l++,后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        //如果 l == r 必须l++ r-- 否则栈溢出
        if (l==r) {
            l+=1;
            r-=1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr,left,r);
        }

        //向右递归
        if (l < right) {
            quickSort(arr,l,right);
        }



    }
}
