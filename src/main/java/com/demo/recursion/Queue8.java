package com.demo.recursion;

public class Queue8 {

    //定义一个max表示共有多少个uagnhou
    int max = 8;
    //定义数组 保存皇后放置位置的结果
    int []  array = new int[max];
    static  int count = 0;

    public static void main(String[] args) {

        //测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);

        System.out.printf("一共有%d次解法", count);
    }

    // 编写一个方法 放置第n个皇后
    // 特别注意 check 是每一次递归时 进入到check中都循环8次 因此会有回溯
    private  void check(int n) {
        if (n == max) {
            print();
            return;
        }

        //依次放入皇后 并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前皇后 n 放到该行的第一列
            array[n] = i;
            //判断当放置第n个皇后到i列时 是否冲突
            if (judge(n)){
                // 不冲突 接着放n+1个皇后 即开始递归
                check(n+1);
            }
            //如果冲突 就继续执行 array[n] = i; 即将第n个皇后 放置到本行 后移的一个位置
        }
    }

    // 查看放置第n个皇后 就去检测该皇后是否和前面已经摆放的皇后冲突
    //n 表示放第n个皇后
    private boolean judge(int n) {
        for (int i = 0; i<n; i++) {
            //1. array[i] == array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //2.  Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    //写一个方法 可以将皇后摆放的位置输出
    private void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        count++;
        System.out.println();
    }
}
