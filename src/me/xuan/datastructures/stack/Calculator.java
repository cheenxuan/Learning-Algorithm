package me.xuan.datastructures.stack;

public class Calculator {

    public static void main(String[] args) {

        //根据前面的分析
        String expression = "7*2*2-(5+1-5+3-4)";//如何处理多位数的问题
        //创建两个栈,一个数栈,一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义相关的变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";

        while (true) {
            //一次得到expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么 做相应的处理
            if (operStack.isOper(ch)) {
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //不为空
                    if (ch == ')') {
                        while (operStack.pick() != '(') {
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = operStack.pop();
                            res = numStack.calc(num1, num2, oper);
                            numStack.push(res);
                        }
                        operStack.pop();
                    } else if (ch != '(' && operStack.priority(ch) <= operStack.priority(operStack.pick())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.calc(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    //直接入栈
                    operStack.push(ch);
                }
            } else {
//                numStack.push(ch - 48);
//                scanVal = scanVal + (ch - 48);
                //档处理多位数时,不能发现是一个数就立即入栈,因为他可能是多位数
                //2.在处理数,需要向expression的表达式的index,向后在看一位,
                //3.因此我们需要定义一个变量 字符串变量,用于拼接

                keepNum += ch;

                //如果ch已经是expression的最后一位,就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                    break;
                }

                //判断下一个字符是不是数字,如果是数字,就继续扫描.如果是运算符则入栈
                if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                    //如果后一位是运算符,则入栈
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }
            }

            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //展示结果
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.calc(num1, num2, oper);
            numStack.push(res);
        }

        System.out.printf("表达式 %s = %d", expression, numStack.pop());
    }
}


class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }

        top++;
        stack[top] = num;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空,没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    public int pick() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return stack[top];
    }

    //返回运算符的优先级,优先级是程序员来确定,优先级使用数字表示
    //数字越大,则优先级越高
    public int priority(int oper) {
        if (oper == ')') {
            return 2;
        } else if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/' || val == '(' || val == ')';
    }

    public int calc(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
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
                res = num2 / num1;
                break;
        }
        return res;
    }

}