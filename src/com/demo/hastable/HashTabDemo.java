package com.demo.hastable;

import java.util.Scanner;

public class HashTabDemo {

    public static void main(String[] args) {

        //创建一个hash表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    int findId = scanner.nextInt();
                    hashTab.findEmpById(findId);
                    break;
                case "exit":
                    scanner.close();
                    break;
                default:
                    break;
            }

        }
    }
}

//表示一个雇员
class Emp {

    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
//创建HashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size; //表示共有多少条链表

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //留一个坑？ 这是不要忘了 分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id得到 该员工应该添加到那条链表
        int empLinkedlistNo = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedlistNo].add(emp);

    }

    //遍历所有的链表 表里hash表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id查找雇员
    public  void findEmpById(int id) {
        //使用散列函数确定查找哪条链表
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);

        if (emp != null) {
            System.out.printf("在第%d条链表中找到 雇员id %d \n", (empLinkedListNo+1), id);
        } else {
            System.out.println("在hash表中没有找到该雇员");
        }




    }


    //编写一个散列函数 使用取模法
    public int hashFun(int id) {
        return  id %size;
    }

}

//创建一个EmpLinkedList 表示链表
class EmpLinkedList {
    //头指针指向第一个Emp 这个链表的head是直接指向第一个雇员的
    private Emp head; //默认为空

    //添加雇员到链表
    //1.假定添加雇员的时候 加在链表的最后
    // id自增长 将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        //添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个 使用一个辅助指针 定位到最后
        Emp curEmp = head;
        while (true){
            if (curEmp.next == null) {//说明到链表最后
                break;
            }
            curEmp = curEmp.next;
        }
        //退出时直接将emp加入链表
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if (head == null) {//说明链表为空
            System.out.println("当前第"+(no+1)+"条链表为空");
            return;
        }
        System.out.print("第"+(no+1)+"链表的信息为");
        Emp curEmp = head; //辅助指针
        while (true) {
            System.out.printf("=> id = %d name = %s \t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                //说明curEmp已经是最后节点
                break;
            }
            curEmp = curEmp.next; //后移遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    //查找到就返回emp 没找到就返回空
    public Emp findEmpById(int id){
        //判空
        if (head == null ){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                //找到
                break;
            }
            //退出
            if (curEmp.next == null){
                //遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}