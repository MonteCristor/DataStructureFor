package com.demo.binarysorttree;

public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();

        //循环的添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

        //测试删除叶子节点
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(12);
        binarySortTree.delNode(5);
        binarySortTree.delNode(2);
        binarySortTree.delNode(9);
        binarySortTree.delNode(10);
//        binarySortTree.delNode(1);


        System.out.println("删除节点后");
        binarySortTree.infixOrder();
        
    }
}
//创建二叉排序树
class BinarySortTree{
    private Node root;

    //查找要删除的节点
    public Node search(int value){
        if (root == null){
            return null;
        } else {
            return root.search(value);
        }
    }
    //查找父节点
    public Node searchParent(int value){
        if (root == null){
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //编写方法
    // 1.返回的是以node为根节点的二叉排序树的最小节点的值
    // 2.删除 node为根节点的二叉排序树的最小节点
    /**
     *
     * @param node 传入的节点 当做一颗二叉排序树的根节点
     * @return 返回的是以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找左节点 就会找到最小值
        while (target.left != null){
            target = target.left;
        }
        //这是target就指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value){
        if (root == null){
            return;
        } else {
            //1.找到要删除的节点
            Node targetNode = search(value);
            //如果没有找到要删除的节点
            if (targetNode == null){
                return;
            }
            //如果当前二叉排序树只有最后一个节点
            if (root .left == null && root.right== null){
                root = null;
                return;
            }

            //去找到targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null){
                if (parent.left != null && parent.left.value == value){//是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value){//是右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null){
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {//删除只有一颗子树的节点
                //如果要删除的节点有左子节点
                if (targetNode.left != null){
                    if (parent != null) {
                        //如果targetNode是iparent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            //targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    //要删除的节点有右子节点
                    //如果targetNode是iparent的左子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            //targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }

        }
    }

    //添加节点的方法
    public void add(Node node){
        if (root == null){
            root = node;//如果root为空 直接让root指向node
        } else {
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空 不能遍历");
        }
    }
}

//创建Node节点
class Node{
    int value;
    Node left;
    Node right;

    //查找要删除的节点

    /**
     *
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点 否则返回null
     *
     */
    public Node search(int value){
        if (value == this.value){//就是该节点
            return  this;
        } else if (value < this.value){
            //如果查找的值小于当前节点 应该向左子树递归查找
            //如果左子节点为空 返回空
            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        } else {
            //如果查找的值大于当前节点 应该向右子树递归查找
            if (this.right == null){
                return null;
            }
           return this.right.search(value);
        }

    }

    //差找要删除的节点的父节点

    /**
     *
     * @param value - >vlaue 要查找的节点的值
     * @return 返回要删除节点的父节点 没有返回null
     */
    public Node searchParent(int value){
        //如果当前节点就是要删除的节点的父节点 就返回
//        System.out.println(value);
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)){
            return this;
        } else {
            //如果查找的值小于当前节点的值 并且当前节点的左子节点不为空
//            System.out.println(value);
            if (value < this.value && this.left != null){
                //向左子树递归查找
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null){
//                System.out.println(value);
                //向右子树递归查找
                return this.right.searchParent(value);
            } else {
                //没找到父节点
                return null;
            }
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    //添加节点的方法
    //递归的形式添加接节点 需要满足二叉排序树的要求
    public void add(Node node){
        if (node == null){
            return;
        }
        //判断传入的节点的值 和当前子树的根节点的关系
        if (node.value < this.value) {
            //如果当前节点左子树节点为空
            if (this.left == null){
                this.left = node;
            } else {
                //递归的向左子树做添加
                this.left.add(node);
            }
        } else {
            //如果 添加的节点值大于当前节点
            if (this.right == null){
                this.right = node;
            } else{
                //递归向右子树递归添加
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);

        if (this.right != null){
            this.right.infixOrder();
        }

    }
}


















