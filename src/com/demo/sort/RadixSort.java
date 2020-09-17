package com.demo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {

    public static void main(String[] args) {

        int arr[] = {53,3,542, 748,14,214};

       /* int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 80000); //生成[0,8000000)数
        }*/

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String dateStr1 = sdf.format(date1);
        System.out.println("排序前的时间是：" + dateStr1);

        radixSort(arr);


        Date date2 = new Date();
        String dateStr2 = sdf.format(date2);
        System.out.println("排序前的时间是：" + dateStr2);
        System.out.println(Arrays.toString(arr));
    }

    //基数排序方法
    public static void radixSort(int[] arr){
        //第一轮(针对每隔元素的个位进行排序处理)

        //定义一个二维数组 表示10个桶 每个桶就是一个一维数组
        //1.二维数组包含10个以为数组
        //2. 为了防止在放入数的时候 数据溢出 则每个以为数组 大小定位arr.length
        //3. 基数排序是使用空间换时间的经典算法
        int [][] bucket = new int[10][arr.length];

        //为了记录每个桶中 实际存放了多少个数据 定义一个以为数组记录每个桶的每次放入的数据个数
        //理解为 bucketElementCounts[0] 记录的就是 bucket[0] 桶放入的数据个数
        int [] bucketElementCounts = new int[10];


        //根据对到 得到最终基数排序代码
        int max = arr[0]; //假设第一个数就是最大数
        for (int i = 1 ; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大的是几位数
        int maxLength = (max + "").length();

        //使用循环处理代码
        for (int i = 0, n = 1; i < maxLength; i++,n*=10) {
            //第i轮(针对每隔元素的位进行排序处理)
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
//                System.out.println("--"+digitOfElement);
//                System.out.println(bucketElementCounts[digitOfElement]);
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;

            }
            //按照桶的顺序 以为数组下标一次取出数据 放入原来数组
            int index = 0;
            //遍历每一个桶 并将桶中数据 放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据 才放入原数组
                if (bucketElementCounts[k] != 0){
                    //循环该桶第K个桶 即第K个以为数组 放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index++]  = bucket[k][l];
                    }
                }
                //第i+1轮处理后 需要将每个bucketElementCounts[k] = 0 !!!
                bucketElementCounts[k] = 0;
            }

//            System.out.println("第"+(i+1)+"轮:"+  Arrays.toString(arr));
        }
/*
        //第一轮(针对每隔元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] % 10;
            //放入到对应的桶中
            System.out.println("--"+digitOfElement);
            System.out.println(bucketElementCounts[digitOfElement]);
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;

        }
        //按照桶的顺序 以为数组下标一次取出数据 放入原来数组
        int index = 0;
        //遍历每一个桶 并将桶中数据 放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据 才放入原数组
            if (bucketElementCounts[k] != 0){
                //循环该桶第K个桶 即第K个以为数组 放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++]  = bucket[k][l];
                }
            }
            //第一轮处理后 需要将每个bucketElementCounts[k] = 0 !!!
            bucketElementCounts[k] = 0;
        }

        System.out.println("第一轮:"+  Arrays.toString(arr));

        //第二轮(针对每隔元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的十位的值
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;

        }
        //按照桶的顺序 以为数组下标一次取出数据 放入原来数组
        index = 0;
        //遍历每一个桶 并将桶中数据 放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据 才放入原数组
            if (bucketElementCounts[k] != 0){
                //循环该桶第K个桶 即第K个以为数组 放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++]  = bucket[k][l];
                }
            }
            bucketElementCounts[k] = 0;
        }

        System.out.println("第二轮:"+  Arrays.toString(arr));


        //第三轮(针对每隔元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的百位的值
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;

        }
        //按照桶的顺序 以为数组下标一次取出数据 放入原来数组
        index = 0;
        //遍历每一个桶 并将桶中数据 放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据 才放入原数组
            if (bucketElementCounts[k] != 0){
                //循环该桶第K个桶 即第K个以为数组 放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++]  = bucket[k][l];
                }

            }
        }

        System.out.println("第三轮:"+  Arrays.toString(arr));*/

    }
}
