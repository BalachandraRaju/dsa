package stack;

import java.util.Stack;

public class PosixEvaluator {

    public static void main(String[] args) {
        String infix = "(((1+2)*(4/2))+((5+7)*(8/2)))";
        String posix = new InfixToPosixConverter().convert(infix);
        int result = new PosixEvaluator().evaluate(posix);
        System.out.println(result);
    }

    private int evaluate(String posix) {
        int n = posix.length();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = posix.charAt(i);
            if (Character.isDigit(ch)) {
                stack.push(Integer.valueOf(String.valueOf(ch)));
            } else {
                int right;
                int left;
                switch (ch) {
                    case '+':
                        right = stack.pop();
                        left = stack.pop();
                        stack.push(left + right);
                        break;
                    case '-':
                        right = stack.pop();
                        left = stack.pop();
                        stack.push(left - right);
                        break;
                    case '*':
                        right = stack.pop();
                        left = stack.pop();
                        stack.push(left * right);
                        break;
                    case '/':
                        right = stack.pop();
                        left = stack.pop();
                        stack.push(left / right);
                        break;
                    case '^':
                        right = stack.pop();
                        left = stack.pop();
                        stack.push(left ^ right);
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal operator " + ch);
                }
            }
        }

        return stack.pop();
    }
}
