package com.demo.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成一个中缀表达式转后缀表达式的功能
        //说明
        //1.
        //2.直接扫描字符串不便 先将表达式字符串转成中缀表达式对应的list

        String expression = "1+((2+3)*4)-5";

        List<String> infixExpressionList = toInfixExpressionList(expression);

        System.out.println(infixExpressionList);

        //将得到的中缀表达式对应的list转成后缀表达式对应的list
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);

        System.out.println("中缀表达式对应的List=" + infixExpressionList);
        System.out.println("后缀表达式对应的List=" + parseSuffixExpressionList);

        System.out.printf("expression=%d",calculate(parseSuffixExpressionList));


/*        //定义一个逆波兰表达式
        //test
        // 4 * 5 - 8 + 60 + 8 / 2  => 4 5 * 8 - 60 + 8 2 / +
//        String suffixExpression = "30 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //表达式的数字和符号 空格隔开
        //思路
        //1.先将"3 4 + 5 * 6 -" 放到ArrayList中
        //2.将ArrayList 传递给一个方法 遍历ArrayList配合栈完成计算
        List<String> rpnList = getListString(suffixExpression);

        System.out.println("rpnLIst=" + rpnList);

        int res = calculate(rpnList);

        System.out.println("计算的结果是=" + res);*/

    }

    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<>(); //符号栈
//        Stack<String> s2 = new Stack<>(); //中间结果栈
        //使用list替换中间结果栈
        ArrayList<String> s2 = new ArrayList<>();

        for (String item : ls) {
            // 如果是一个数 加入s2
            if (item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")){
                s1.push(item);
            } else if (item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将 ( 弹出
            } else {
                //item的优先级小于等于栈顶运算符
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //将item压入栈顶
                s1.push(item);
            }
        }
        //将s1剩余元素加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }











    //将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List 存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0; //指针用于遍历中缀表达式字符串
        String str; //对多位数的拼接
        char c; //每遍历一个字符串 就放入c
        do{
            // c为非数字 加入到ls
            if ((c=s.charAt(i))< 48 || (c=s.charAt(i)) < 57){
                ls.add("" + c);
                i++; //i需要后移
            } else {
                //考虑多位数
                str = ""; //str置为空串
                while (i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(8)) <= 57){
                    str += c; //拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());

        return ls;
    }



    //将一个逆波兰表达式 一次将数据和计算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffExpression分割
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();

        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的计算
    public static int calculate(List<String> ls) {
        //创建一个栈 一个
        Stack<String> stack = new Stack<>();

        //遍历 ls
        for (String item : ls) {
            //使用正则表达式取数
            if (item.matches("\\d+")) {
                //匹配多位数
                //入栈
                stack.push(item);
            } else {
                //pop 出两个数 运算 再入栈
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());

                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                } else if (item.equals("-")){
                    res = num1 - num2;
                } else if (item.equals("*")){
                    res = num1 * num2;
                } else if (item.equals("/")){
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }

                //吧res入栈
                stack.push(res + "");
            }
        }

        return Integer.valueOf(stack.pop());
    }


}

// 返回运算符优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}






















