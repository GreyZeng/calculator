package com.hui.calculator.models;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.Stack;

import static com.google.common.base.Objects.equal;
import static com.hui.calculator.core.Generator.generateAvailableOperators;
import static com.hui.calculator.models.Constants.*;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * @author zenghui
 * @date 2017/12/24
 */
public class BinaryTree {

    private Node root;


    public static BinaryTree create(String exp) {
        List<String> out = Lists.newCopyOnWriteArrayList();
        infixToSuffix(out,exp);
        return construct(out);
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
    public static void infixToSuffix(List<String> output, String exp) {
        Stack<String> theStack = new Stack<>();
        if (isEmpty(exp)) {
            output =  Lists.newCopyOnWriteArrayList();
        }
        String[] infix = exp.split(SPACE);
        int length = infix.length;
        for (int j = 0; j < length; j++) {
            String item = infix[j];
            switch (item) {
                case PLUS:
                case MINUS:
                    gotOper(output,item, 1, theStack);
                    break;
                case MULTIPLY:
                case DIVIDE:
                    gotOper(output,item, 2, theStack);
                    break;
                case LEFT_PARENTHESES:
                    theStack.push(item);
                    break;
                case RIGHT_PARENTHESES:
                    gotParen(output,theStack);
                    break;
                default:
                    output.add(item);
                    break;
            }
        }
        while (!theStack.isEmpty()) {
            output.add(theStack.pop());
        }
    }


    private BinaryTree(Node root) {
        this.root = root;
    }

    private static void gotOper(List<String> output, String opThis, int prec1, Stack<String> theStack) {
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

    private static void gotParen(List<String> output, Stack<String> theStack) {
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
