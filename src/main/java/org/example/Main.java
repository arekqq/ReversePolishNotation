package org.example;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;

/*
Reverse Polish Notation is a way of noting mathematical expressions without need of using parenthesis to keep operations order.
For example:
((2 + 7) / 3 + (14 − 3) * 4) / 2
Can be written in RPN as:
2 7 + 3 / 14 3 − 4 * + 2 /
An operation is applied to preceding operands, and then the result is treated as a single operand.
Write a function that accepts a single String with RPN expression and returns a result as a number.
Example:
Input: 6 3 5 + 8 * +
Output: 70
Input: 10 6 9 3 + -11 * / * 17 + 5 +
Output: 22
 */
public class Main {
    public static void main(String[] args) {
        var result = calculateRpn("");
        System.out.println(result);
    }

    public static int calculateRpn(String input) {

        String[] splitInput = input.split(" ");
        Deque<String> deque = new LinkedList<>();
        List<String> operators = Arrays.stream(Operator.values()).map(a -> a.operator).toList();

        for (String arg : splitInput) {
            if (operators.contains(arg)) {
                Operator operator = Operator.fromOperator(arg);
                int arg1 = Integer.parseInt(deque.pop());
                int arg2 = Integer.parseInt(deque.pop());
                int result = operator.function.apply(arg2, arg1);
                deque.push(String.valueOf(result));
            } else {
                deque.push(arg);
            }

        }
        return Integer.parseInt(deque.pop());
    }

    private enum Operator {
        SUM("+", Integer::sum),
        SUBSTRACT("-", (a, b) -> a - b),
        MULTIPLY("*", (a, b) -> a * b),
        DIVIDE("/", (a, b) -> a / b);

        private final String operator;
        private final BinaryOperator<Integer> function;

        Operator(String operator, BinaryOperator<Integer> function) {
            this.operator = operator;
            this.function = function;
        }

        public static Operator fromOperator(String operator) {
            return Arrays.stream(Operator.values())
                    .filter(a -> a.operator.equals(operator))
                    .findFirst()
                    .orElseThrow();
        }
    }
}
