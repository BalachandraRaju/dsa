package stack;

import java.util.Stack;

public class InfixToPosixConverter {

    public static void main(String[] args) {
        String infix = "(((a+b)*(c-d))+((e+f)*(g-h)))";
        new InfixToPosixConverter().convert(infix);
    }

    public String convert(String infix) {
        int n = infix.length();
        Stack<Character> stack = new Stack<>();
        StringBuilder posix = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = infix.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                posix.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    char top = stack.pop();
                    posix.append(top);
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && prec(ch) <= prec(stack.peek())) {
                    char top = stack.pop();
                    posix.append(top);
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            char ch = stack.pop();
            posix.append(ch);
        }

        System.out.println(posix.toString());
        return posix.toString();
    }

    private int prec(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
}
