package com.hui.calculator.models;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.Stack;

import static com.google.common.base.Objects.equal;
import static com.google.common.collect.Lists.newArrayList;
import static com.hui.calculator.core.Generator.generateAvailableOperators;
import static com.hui.calculator.models.Constants.*;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * @author zenghui
 * @date 2017/12/24
 */
public class BinaryTree {

    private Node root;

    private static List<String> output = newArrayList();

    public static BinaryTree create(String exp) {
        output.clear();
        return construct(infixToSuffix(exp));
    }

    public static String generateExpression(Node root) {
        if (root.left == null && root.right == null) {
            return root.value;
        } else if (equal(root.value, PLUS) || equal(root.value, MULTIPLY)) {
            String left = generateExpression(root.left);
            String right = generateExpression(root.right);
            if (compare(left, right) <= 0) {
                return root.value + left + right;
            } else {
                return root.value + right + left;
            }
        } else {
            return root.value + generateExpression(root.left) + generateExpression(root.right);
        }

    }

    public Node getRoot() {
        return this.root;
    }

    public static List<String> infixToSuffix(String exp) {
        Stack<String> theStack = new Stack<>();
        if (isEmpty(exp)) {
            return newArrayList();
        }
        String[] infix = exp.split(SPACE);
        int length = infix.length;
        for (int j = 0; j < length; j++) {
            String item = infix[j];
            switch (item) {
                case PLUS:
                case MINUS:
                    gotOper(item, 1, theStack);
                    break;
                case MULTIPLY:
                case DIVIDE:
                    gotOper(item, 2, theStack);
                    break;
                case LEFT_PARENTHESES:
                    theStack.push(item);
                    break;
                case RIGHT_PARENTHESES:
                    gotParen(theStack);
                    break;
                default:
                    output.add(item);
                    break;
            }
        }
        while (!theStack.isEmpty()) {
            output.add(theStack.pop());
        }
        return output;
    }

    private BinaryTree(Node root) {
        this.root = root;
    }

    private static void gotOper(String opThis, int prec1, Stack<String> theStack) {
        while (!theStack.isEmpty()) {
            String opTop = theStack.pop();
            if (equal(opTop, LEFT_PARENTHESES)) {
                theStack.push(opTop);
                break;
            } else {
                int prec2;
                if (equal(opTop, PLUS) || equal(opTop, MINUS)) {
                    prec2 = 1;
                } else {
                    prec2 = 2;
                }
                if (prec2 < prec1) {
                    theStack.push(opTop);
                    break;
                } else {
                    output.add(opTop);
                }
            }
        }
        theStack.push(opThis);
    }

    private static void gotParen(Stack<String> theStack) {
        while (!theStack.isEmpty()) {
            String item = theStack.pop();
            if (equal(item, LEFT_PARENTHESES)) {
                break;
            } else {
                output.add(item);
            }
        }
    }

    private static BinaryTree construct(List<String> post) {
        Stack<Node> stack = new Stack();
        Node parent;
        Node right;
        Node left;
        for (String item : post) {
            if (!isOperator(item)) {
                parent = new Node(item);
                stack.push(parent);
            } else {
                parent = new Node(item);
                right = stack.pop();
                left = stack.pop();
                parent.right = right;
                parent.left = left;
                stack.push(parent);
            }
        }
        parent = stack.peek();
        stack.pop();
        return new BinaryTree(parent);
    }

    private static boolean isOperator(String item) {
        return ArrayUtils.contains(generateAvailableOperators(true), item);
    }
}

class Node {
    String value;
    Node left;
    Node right;

    Node(String value) {
        this.value = value;
    }
}
