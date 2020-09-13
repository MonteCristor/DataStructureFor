package com.demo.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {

        //测试中序线索二叉树
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树后面递归创建 现在简单处理手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试中序线索化
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号前驱节点测试:" + leftNode);
        System.out.println("10号后继节点测试:" + rightNode);

    }
}

//定义一个ThreadedBinaryTree 二叉树 实现了线索化功能的二叉树
class ThreadedBinaryTree{
    private HeroNode root;

    //为了实现线索化 需要穿件一个当前节点的前驱节点指正
    //在递归线索化是 pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //对二叉树中序线索化的方法
    //node 当前需要线索化的节点
    public void threadedNodes(HeroNode node){
        //如果node== null 不能线索化
        if (node == null){
            return;
        }

        // 1.先线索化左子树
        threadedNodes(node.getLeft());
        // 2.线索化当前节点 难点

        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //当前节点的做指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型
            node.setLeftType(1);
        }

        //处理后继节点
        if (pre != null && pre.getRight() == null){
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }

        //!!!每处理一个节点后 让当前节点是下一个节点的前驱节点
        pre = node;

        // 3.再线索化右子树
        threadedNodes(node.getRight());

    }

    //删除节点
    public void delNode(int no) {
        if (root != null){
            //如果只有一个root节点 需要立即判断root是否是要删除的节点
            if (root.getNo() == no){
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树不能删除");
        }
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空， 无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空， 无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空， 无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if (this.root != null){
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }


    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if (this.root != null){
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if (this.root != null){
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

}

//创建HeroNode
class HeroNode {
    private int no;
    private String name;

    private HeroNode left;//默认null
    private HeroNode right;//默认null

    //1.如果leftType == 0 表示指向的是左子树 如果 1 表示指向前驱节点
    //2.乳沟rightType == 0 表示指向右子树 如果1 表示指向后继节点
    private int leftType;
    private int rightType;



    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    //递归删除节点
    //1.如果删除的节点是叶子节点 则删除该节点
    //2.如果删除的节点是非叶子节点 则删除该子树
    public void delNode(int no){
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if (this.left != null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }
    }





    //前序遍历
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树
        if (this.left != null){
            this.left.preOrder();
        }
        //递归向右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if (this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);

        //递归向右子树中序遍历
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null){
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找

    /**
     *
     * @param no 查找no
     * @return 查找到就返回Node 否则返回null
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序遍历~~");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        //1.判断当前节点是否为空 不为空 递归前序查找
        //2.如果左递归前序查找 找到 返回
        if (this.left != null){
            resNode = this.left.preOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        //1.判断当前节点是否为空 不为空 递归前序查找
        //2.如果有递归前序查找 找到 返回
        if (this.left != null){
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        //1.判断当前节点是否为空 不为空 递归中序查找
        //2.如果左递归前序查找 找到 返回
        if (this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入中序遍历~~");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }

        if (this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;
    }


    //后序遍历查找
    public HeroNode postOrderSearch(int no){

        HeroNode resNode = null;
        //1.判断当前节点是否为空 不为空 递归中序查找
        //2.如果左递归前序查找 找到 返回
        if (this.left != null){
            resNode = this.left.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }


        if (this.right != null){
            resNode = this.right.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入后序遍历~~");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }

        return resNode;
    }

}
