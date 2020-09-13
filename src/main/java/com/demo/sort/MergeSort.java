package com.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {

    public static void main(String[] args) {


       /* int arr[] = {8,4,5,7,1,3,6,2,0,234};*/

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成[0,8000000)数
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String dateStr1 = sdf.format(date1);
        System.out.println("排序前的时间是：" + dateStr1);

        int temp[] = new int[arr.length];

        mergeSort(arr, 0 ,arr.length - 1, temp);


        Date date2 = new Date();
        String dateStr2 = sdf.format(date2);
        System.out.println("排序前的时间是：" + dateStr2);

//        System.out.println(Arrays.toString(arr));

    }

    //合并

    /**
     *
     * @param arr 待排序原始数组
     * @param left 左边有序序列出事索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 中转数组
     */
    public static void merge(int [] arr, int left ,int mid, int right, int [] temp) {
//        System.out.println("XXXXX");


        int i = left; //初始化i 左边有序序列的初始索引
        int j = mid + 1;//初始化j 右边有序序列的初始索引
        int t = 0; //指向temp数组的当前索引

        //1先把左右两边的有序数据按照规则填充到temp数组 知道左右两边有序序列有一边全部处理完毕
        while(i <= mid && j <= right) {
            //如果左边有序序列的当前元素 小于等于右边有序序列当前元素
            // 将左边的当前元素 拷贝到temp数组
            // t++ i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                //反之将右边有序序列的当前元素 填充到temp数组
                temp[t] = arr[j];
                j += 1;
                t += 1;
            }
        }

        //2吧有剩余数据的一边的数据依次全部填充到temp
        while ( i <= mid) {
            //左边有序序列还有剩余元素 全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while(j <= right) {
            temp [t] = arr[j];
            t += 1;
            j += 1;
        }


        //3将temp数组重新拷贝到arr
        //并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;

        //第一次合并是 tempLeft 0 left 1 // tempLeft = 2 right = 3 //tL = 0 right = 3
        //最后一个ok

//        System.out.println("tempLeft=" + tempLeft + " right=" + right);
        while(tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }


    }

    public static void mergeSort(int [] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1,right, temp);

            //到合并时
            merge(arr,left,mid,right,temp);
        }

    }
}

