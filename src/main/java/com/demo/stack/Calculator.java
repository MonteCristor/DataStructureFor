package com.demo.stack;

public class Calculator {

    public static void main(String[] args) {

        //完成表达式的运算
        String expression = "7*2*2-5+1-5+3-4";
        //数栈 符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义需要的相关变量
        int index = 0; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; // 将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多为数

        //开始用while循环扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index +1).charAt(0);

            //判断ch是什么 做相应的处理
            if(operStack.isOper(ch)){
                //如果是运算符
                if (operStack.isEmpty()){
                    operStack.push(ch);
                } else {
                    //处理 比较
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //结果
                        numStack.push(res);
                        //符号入栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                }
            } else {
                //数字
//                numStack.push(ch - 48);
                //1.处理多位数
                //2.处理数 需要向expression后再看以为 数就扫描 符号入栈
                //3.定义一个字符串变量 用于拼接

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression的最后一位

                if (index == expression.length() -1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {

                    //判断是否为数字
                    // 只是看以为 不index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //后一位是运算符 入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum 清空
                        keepNum = "";
                    }
                }
            }


            //index + 1, 并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //得到最后的结果
        while (true) {
            //如果符号栈为空 则计算到最后的结果 数栈中只有一个数字了
            if(operStack.isEmpty()){
                break;
            }

            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);

            numStack.push(res);
        }

        //将数栈的结果pop出就是结果.
        int res2 = numStack.pop();
        System.out.printf("表达是%s = %d", expression, res2);

    }

}
    //栈
    class ArrayStack2 {
        private int maxSize; //栈的大小
        private int[] stack; //数组模拟栈
        private int top = -1; //栈顶 初始化-1

        public ArrayStack2(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[this.maxSize];
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

       //增加一个方法peek
       public int peek(){
            return stack[top];
       }

        //入栈
        public void push(int value) {
            //先判断是否满
            if (isFull()) {
                System.out.println("栈满");
                return;
            }
            top++;
            stack[top] = value;
        }

        //出栈 酱栈顶数据返回
        public int pop() {
            //判断是否为空
            if (isEmpty()) {
                //抛出异常
                throw new RuntimeException("栈空， 美欧数据。。。");
            }

            int value = stack[top];
            top--;
            return value;
        }

        //遍历栈 遍历时需要从栈顶开始显示数据
        public void list() {

            if (isEmpty()) {
                System.out.println("栈空，没有数据。。。");
                return;
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d]=%d \n", i, stack[i]);
            }

        }

        //返回优先级 自定义优先级  返回数字越大 优先级越高
        public int priority( int oper) {
            if (oper == '*' || oper == '/'){
                return 1;
            } else if(oper == '+' || oper == '-'){
                return  0;
            } else {
                return  -1; //只有四则运算
            }

        }

        //判断是否运算符
        public boolean isOper(char val) {
            return val == '+' || val == '-' || val == '*' || val == '/';
        }

        //计算方法
        public int cal(int num1, int num2, int oper){
            int res = 0; //res 存放计算结果

            switch (oper){
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num2 - num1;
                    break;
                case '*':
                    res = num2 * num1;
                    break;
                case '/':
                    res = num2 /num1;
                    break;
                default:
                    break;
            }
            return  res;
        }

    }

