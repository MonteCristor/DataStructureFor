package com.demo.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {

    public static void main(String[] args) {

       /* int arr[]  = {4,6,8,5,9,-1,90,89,56,-999};*/

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成[0,8000000)数
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String dateStr1 = sdf.format(date1);
        System.out.println("排序前的时间是：" + dateStr1);

        heapSort(arr);

        Date date2 = new Date();
        String dateStr2 = sdf.format(date2);
        System.out.println("排序前的时间是：" + dateStr2);
    }

    //堆排序方法
    public static void heapSort(int arr[]){
        int temp = 0;
        System.out.println("堆排序");

 /*       //分步完成
        adjustHeap(arr, 1, arr.length);
        System.out.println("第一次" + Arrays.toString(arr));//4 9 8 5 6

        adjustHeap(arr, 0, arr.length);
        System.out.println("第二次" + Arrays.toString(arr));//9 6 8 5 4*/

        //最终代码
        //将无序序列构建成一个堆 根据升序降序需求选择大顶推或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
                adjustHeap(arr, i, arr.length);
        }

        /*1.将堆顶元素与末尾元素交换 将最大元素 沉 到数组末端
          2.重新调整解结构 使其满足堆定义 然后继续交换堆顶元素与当前末尾元素 反复执行调整+交换步骤 直到整个序列有序*/

        for (int j = arr.length -1; j > 0  ; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0 , j);
        }
//        System.out.println("第X次" + Arrays.toString(arr));

    }

    //将一个数组(二叉树) 调整成一个大顶推

    /**
     * 功能：将以i对应的非叶子节点的树调整成大顶推
     * int arr[]  = {4,6,8,5,9}; => i = 1 => adjustHeap => {4,9,8,5,6}
     * 如果再次调用 adjustHeap 传入的是i= 0 => {4,9,8,5,6} => {9,6,8,5,4}
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的索引
     * @param length 对多少个元素进行调整 length逐渐减少
     */
    public static void  adjustHeap(int arr[], int i, int length){

        int temp = arr[i]; //先取出当前元素的值 保存在临时变量
        //开始调整
        // 1. int k = i * 2 + 1 是i节点的左子节点
        for (int k = i * 2 + 1; k < length ; k = k * 2 + 1) {
            if (k+ 1 < length && arr[k] < arr[k + 1]){//说明左子节点的值小于右值节点的值
                k++;//k指向右子节点
            }
            if (arr[k] > temp){//如果子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k; //!!!!!! i指向 K继续循环比较
            } else {
                break;//
            }
        }
        //当for循环结束后 已经得到将以i为父节点的树的最大值 放在了最顶上
        arr[i] = temp;//将temp值放到调整后的位置
    }
}


