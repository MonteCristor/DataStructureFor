package com.demo.huffmancode;

import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {

        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();

        byte[] hufumanCodesBytes = huffmanZip(contentBytes);

        System.out.println("压缩后的结果是:" +Arrays.toString(hufumanCodesBytes)+" 长度为："+hufumanCodesBytes.length);

        //测试byteToBitString
        String s = byteToBitString(true, (byte) 1);
        System.out.println(s);


        //如何将数据进行解压(解码)
//        System.out.println(content.length());

        //分布过程
       /* List<Node> nodes = getNodes(contentBytes);

//        System.out.println(nodes);

        //测试创建的赫夫曼
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        preOrder(huffmanTreeRoot);

        //测试是否生成对应的赫夫曼编码
        Map<Byte, String> huffmanCodes1 = getCodes(huffmanTreeRoot);

        System.out.println("生成的赫夫曼编码表:"+ huffmanCodes1);
        //测试
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes1);

        System.out.println("huffmanCodeBytes=" +Arrays.toString(huffmanCodeBytes));

        //发送huffmanCodeBytes 数组*/

    }

    //完成数据解压
    //思路 HuffmanCodeBytes->[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //1.将HuffmanCodeBytes转成赫夫曼对应点二进制的字符串字符串 "1010100010111..."
    //2.赫夫曼对应的二进制的字符串 "1010100010111..." =>对照 赫夫曼编码 重新转成字符串 => "i like like like java do you like a java"


    //步骤2
    //编写一个方法 完成对压缩数据的解码

    /**
     *
     * @param huffmanCodes
     * @param huffmanBytes
     * @return
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes){


        return null;
    }






    /** 步骤1
     * 将一个byte 转成一个二进制的字符串
     * @param b
     * @param flag 标志是否需要补高位 如果是true表示需要补高位 false表示不补
     * @return 是该b 对应的二进制字符串 按补码返回
     */
    private static String byteToBitString(boolean flag, byte b){
        //使用一个变量保存 b
        int temp = b; //将b 转成int
        //如果是正数 存在补高位的问题

        if (flag){
            temp |= 256; // 按位与 1 0000 0000    0000 0001
        }

        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制补码

        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }








    //使用一个方法 将前面的方法封装起来 便于掉哦用

    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes){

        //1.根据字节数组得到List<node>节点
        List<Node> nodes = getNodes(bytes);

        //2.根据nodes创建的赫夫曼
        Node huffmanTreeRoot = createHuffmanTree(nodes);

        //3.根据赫夫曼树 生成对应的赫夫曼编码
        Map<Byte, String> huffmanCodes1 = getCodes(huffmanTreeRoot);

        //4.根据生成的赫夫曼编码压缩 压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes1);

        return huffmanCodeBytes;
    }


    //编写一个方法 将一个字符串对应的byte数组 通过生成的赫夫曼编码表 返回一个赫夫曼编码表处理过后的Byte[]数组

    /**
     *
     * @param bytes 原始字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip (byte[] bytes, Map<Byte, String> huffmanCodes) {

        //1.利用赫夫曼编码表 将bytes 转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b: bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("测试stringBuilder:"+stringBuilder);
        //将得到的str转成byte[]
        //统计返回的 byte[] huffmanCodeBytes 长度
        //一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的 bytes数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0 ;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i+=8) {//因为是每8位对应一个byte 所以步长 + 8
            String strByte;
            if (i + 8 > stringBuilder.length()){//不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i+8);
            }

            //将strByte 装成一个byte 放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
            index ++;
        }

        return huffmanCodeBytes;
    }

    //生成赫夫曼树对应的赫夫曼编码
    //1.将赫夫曼编码表存放在map<Byte,String>中
    static Map huffmanCodes = new HashMap<Byte, String>();
    //2.在生成赫夫曼编码表是需要 拼接路径 定义一个StringBuilder 存储某个叶子节点的路径
    static StringBuilder sringBuilder = new StringBuilder();

    //重载getCodes
    private static Map<Byte, String> getCodes(Node root){
        if (root == null) {
            return null;
        } else {
            //处理root的左子树
            getCodes(root.left,"0", sringBuilder);
            //处理root的右子树
            getCodes(root.right, "1", sringBuilder);
        }
        return huffmanCodes;
    }

    /**
     * 功能 将传入的node节点的所有叶子节点的赫夫曼编码得到 并存放到huffmanCodes集合中
     * @param node 传入的节点
     * @param code  路径 左子节点是0 右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);

        stringBuilder2.append(code);
        if (node != null){//如果node == null不处理
            //判断当前node 是叶子节点还是非叶子节点
            if(node.data == null){//非叶子节点
                //递归处理
                //向左
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right,"1", stringBuilder2);
            } else {//说明是一个叶子节点
                //就表示找到某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }

    }

    //前序遍历的方法
        private static void preOrder(Node root){
            if (root != null){
                root.preOrder();
            } else {
                System.out.println("赫夫曼树为空");
            }
        }


    /**
     *
     * @param bytes 接收字节数组
     * @return 返回list
     */
    private static List<Node> getNodes(byte[] bytes){
        //1.创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        //2.遍历bytes 统计每一个byte出现的次数 -> map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1);
            } else {
                counts.put(b,count + 1);
            }
        }
        //吧每一个键值对转成一个Node对象 并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }

        return nodes;
    }

    //通过List 创建赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            //排序 从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树 它的根节点没有data 只有权值
            Node parent = new Node(null,leftNode.weight +rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理过的二叉树删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到node
            nodes.add(parent);
        }
        //nodes最后的节点就是赫夫曼数据的根节点
        return nodes.get(0);
    }
}
//创建Node 带数据和权值
class Node implements Comparable<Node>{
    Byte data;//存放数据(字符)本身 比如'a' => 97 ' ' => 32
    int weight;//权值 表示字符出现的次数

    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right!= null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}