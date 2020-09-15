package main.com.demo.tree;

public class HeapSort {

    public static void main(String[] args) {

        int arr[]  = {4,6,8,5,9};

    }

    //堆排序方法
    public static void heapSort(int arr[]){
        System.out.println("堆排序");
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


