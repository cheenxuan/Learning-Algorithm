package me.xuan.datastructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotationDemo {

    public static void main(String[] args) {

        //完成将一个中缀表达式转换为后缀表达式
        //说明
        //1.因为直接对一个字符串进行操作,不方便.因为先将中缀表达式
        String expression = "1+({[((2+3)*4)-5]*6+7}*8)/2";

        //2.中缀表达式字符串  => 转换为 list
        List<String> infixExpressionList = toInfixExpressionList(expression);

        System.out.println("infixExpressionList = " + infixExpressionList);

        //3.将得到的中缀表达式对应的list => 后缀表达式的list
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("suffixExpressionList = " + suffixExpressionList);


        int res = calculate(suffixExpressionList);

        System.out.println("计算的结果 res = " + res);

//        //先定义逆波兰表达式
//        //(3+4)*5-6  => 3 4 + 5 * 6 -
//        //为了方便,逆波兰表达式的数字和符号使用空格隔开
//        String suffixExpression = "30 4 + 5 * 6 -";
//
//        //1.先将"3 4 + 5 * 6 -"放到ArrayList中
//        //2.将ArrayList 传递给一个方法,遍历 配合栈 完成计算
//
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println("rpnList = " + rpnList);
//
//
//        int res = calculate(rpnList);
//
//        System.out.println("计算的结果 res = " + res);
    }

    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //1.初始化两个栈,第二个栈在整个转换过程中,没有pop操作,而且后面还需要逆序输出
        //因此比较麻烦,这里直接使用List<String>
        Stack<String> s1 = new Stack<>();
        ArrayList<String> s2 = new ArrayList<>();

        //遍历ls
        for (String item : ls) {
            //1.如果是一个数 加入到s2
            if (item.matches("^\\d+$")) {
                s2.add(item);
            } else if (item.equals("{")) {
                s1.push(item);
            } else if (item.equals("}")) {
                while (!s1.peek().equals("{")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else if (item.equals("[")) {
                s1.push(item);
            } else if (item.equals("]")) {
                while (!s1.peek().equals("[")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                //当item的优先级小于等于s1栈顶的运算符
                while (s1.size() != 0 && Operation.getValue(item) <= Operation.getValue(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }

        return s2;
    }

    //将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String expression) {
        ArrayList<String> list = new ArrayList<>();
        int index = 0;//索引  用于遍历  中缀表达式字符串
        String str = "";
        char c;
        do {
            c = expression.charAt(index);
            //如果c是一个非数字,就需要加入到list
            if (c < 48 || c > 57) {
                list.add(String.valueOf(c));
                index++;
            } else {
                str = "";
                while (index < expression.length() && (c = expression.charAt(index)) >= 48 && (c = expression.charAt(index)) <= 57) {
                    str += c;
                    index++;
                }
                list.add(str);
            }
        } while (index < expression.length());

        return list;
    }

    //将一个逆波兰表达式,依次将数据和运算符 放入到到arrayList中
    public static List<String> getListString(String suffuxExpression) {
        //将suffixExpression分隔
        String[] split = suffuxExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();

        for (String ele : split) {
            list.add(ele);
        }

        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> list) {

        //创建一个栈
        Stack<String> stack = new Stack<>();

        for (String item : list) {
            //正则表达式
            if (item.matches("^\\d+$")) {//匹配多位数
                stack.push(item);
            } else {
                //pop出两个数进行计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());

                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res 入栈
                stack.push(String.valueOf(res));
            }
        }

        return Integer.parseInt(stack.pop());
    }

}

//可以返回运算符对应的优先级
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
