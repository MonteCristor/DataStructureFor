package com.demo.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {

    public static void main(String[] args) {
        int arr[] = {13,7,8,3,29,6,1};

        Node rootNode = createHuffmanTree(arr);

        //测试
        preOder(rootNode);

    }

    //编写一个前序遍历方法、
    public static void preOder(Node root){

        if (root != null){
            root.preOrder();
        } else {
            System.out.println("空树不能遍历");
        }
    }
    
    //创建赫夫曼树

    /**
     *
     * @param arr 需要创建赫夫曼树的数组
     * @return 创建好后的赫夫曼树的root几点
     */
    public static Node createHuffmanTree(int [] arr){
        //操作方便
        //1.遍历arr数组
        //2.将arr的每个元素构建为一个Node
        //3.将Node 放入到ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();

        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //处理过程是循环过程
        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);
            System.out.println("nodes:"+nodes);

            //取出根节点权值最小的两颗二叉树
            //1.取出权值最小的节点(二叉树)
            Node leftNode = nodes.get(0);
            //2.取出第二小的节点(二叉树)
            Node rightNode = nodes.get(1);

            //3.构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //4.从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //5.将parent加入到nodes
            nodes.add(parent);
        }
        //返回赫夫曼树的root节点
        return nodes.get(0);

      /*  //第一次处理后
        Collections.sort(nodes);
        System.out.println("第一次处理过后："+nodes);*/

    }
}
//创建节点类
//支持排序 实现comparable
class Node implements  Comparable<Node>{
    int value;//节点的权值


    char c;//字符
    Node left;//左子节点
    Node right;//右子节点

    //前序遍历
    public void preOrder(){
        System.out.println(this);

        if (this.left != null){
            this.left.preOrder();
        }

        if (this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int value){
        this.value= value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大
        return this.value - o.value;
    }
}
