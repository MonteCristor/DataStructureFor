package com.demo.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {

        //测试压缩文件
/*        String srcFile = "D:\\src\\black.png";
        String dstFile = "D:\\src\\dst2.zip";

        zipFile(srcFile,dstFile);
        System.out.println("压缩文件成功");*/

        //测试解压文件
        String zipFile = "D:\\src\\dst.zip";
        String dstFile = "D:\\src\\src2.png";
        unZipFile(zipFile, dstFile);
        System.out.println("解压成功");

      /*  String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();

        byte[] hufumanCodesBytes = huffmanZip(contentBytes);

        System.out.println("压缩后的结果是:" +Arrays.toString(hufumanCodesBytes)+" 长度为："+hufumanCodesBytes.length);

        //测试byteToBitString
       *//* String s = byteToBitString(true, (byte) 1);
        System.out.println(s);*//*

        byte[] sourceBytes = decode(huffmanCodes, hufumanCodesBytes);
        System.out.println("原来的字符串=" + new String(sourceBytes));*/


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

    //编写一个方法 完成对压缩文件的解压

    /**
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 文件解压的路径
     */
    public static void unZipFile(String zipFile, String dstFile){

        //定义文件的输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;

        try {
            //创建一个文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到文件中
            os.write(bytes);

        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }





    //编写方法 讲一个文件进行压缩

    /**
     *
     * @param srcFile 传入的需要压缩的文件的全路径
     * @param dstFile 压缩后将压缩文件放到的目录
     */
    public static void zipFile(String srcFile, String dstFile){
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos =null;

        //创建文件的输入流
        FileInputStream is = null;
        try {
             is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte数组
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建一个文件输出流 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);//先把
            //以对象流的方式写入赫夫曼的编码 是为了以后恢复源文件是使用
            //一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }







    //完成数据解压
    //思路 HuffmanCodeBytes->[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //1.将HuffmanCodeBytes转成赫夫曼对应点二进制的字符串字符串 "1010100010111..."
    //2.赫夫曼对应的二进制的字符串 "1010100010111..." =>对照 赫夫曼编码 重新转成字符串 => "i like like like java do you like a java"

    /** 步骤1
     * 将一个byte 转成一个二进制的字符串
     * @param b
     * @param flag 标志是否需要补高位 如果是true表示需要补高位 false表示不补 如果是最后一个字节 无需补高位
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


    //步骤2
    //编写一个方法 完成对压缩数据的解码
    /**
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes){

        //1.先得到HuffmanBytes 对应的二进制字符串 形式 "1010100010111..."
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是否最后一个字节
            boolean flag = (i == huffmanBytes.length -1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
//        System.out.println("解码后赫夫曼字节数组对应的二进制字符串="+stringBuilder.toString());
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换 应为反向查询
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
//        System.out.println("map =" + map);

        //创建一个集合 存放byte
        ArrayList<Byte> list = new ArrayList<>();
        //i可以理解成索引 扫描stringBuilder
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //1010100010111...
                //递增的取出key
                String key = stringBuilder.substring(i, i + count);//i不动 让count移动 指定匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count; //i直接移动到 count
        }
        //当for循环结束后 list中就存放了所有的字符
        //把list中的数据放入到byte[] 并返回
        byte[] b= new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }

        return b;
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