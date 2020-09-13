package com.demo.search;

public class SeqSearch {
    public static void main(String[] args) {

        int arr [] = {1,9,11,-1,34,89}; //无序数组

        int i = seqSearch(arr, -11);

        System.out.println("索引为："+ i );

    }

    public static int seqSearch(int arr[], int value) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
