package com.cp.core;


import static com.google.common.base.Objects.equal;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.compare;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/24
 */
public class ExpTree {
    private Node root;

    public ExpTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(String data) {
        root = insert(root, data);
    }

    private Node insert(Node node, String data) {
        if (node == null)
            node = new Node(data);
        else {
            if (node.getRight() == null)
                node.right = insert(node.right, data);
            else
                node.left = insert(node.left, data);
        }
        return node;
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node r) {
        if (r == null)
            return 0;
        else {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }

    public boolean search(String val) {
        return search(root, val);
    }

    private boolean search(Node r, String val) {
        if (equal(r.getData(), val))
            return true;
        if (r.getLeft() != null)
            if (search(r.getLeft(), val))
                return true;
        if (r.getRight() != null)
            if (search(r.getRight(), val))
                return true;
        return false;
    }

    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        return inOrder(root, sb);
    }

    private String inOrder(Node r, StringBuilder sb) {
        if (r != null) {
            inOrder(r.getLeft(), sb);
            sb.append(r.getData()).append(SPACE);
            inOrder(r.getRight(), sb);
        }
        return sb.toString();
    }

    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        return preOrder(root, sb);
    }

    private String preOrder(Node r, StringBuilder sb) {
        if (r != null) {
            sb.append(r.getData()).append(SPACE);
            preOrder(r.getLeft(), sb);
            preOrder(r.getRight(), sb);
        }
        return sb.toString();
    }

    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        return postOrder(root, sb);
    }

    private String postOrder(Node r, StringBuilder sb) {
        if (r != null) {
            postOrder(r.getLeft(), sb);
            postOrder(r.getRight(), sb);
            sb.append(r.getData()).append(SPACE);
        }
        return sb.toString();
    }

    public String generateExpression() {
        return this.generateExpression(root);
    }

    private String generateExpression(Node root) {
        if (root.left == null && root.right == null) {
            return root.getData();
        } else if (equal(root.getData(), "+") || equal(root.getData(), "×")) {
            String left = generateExpression(root.left);
            String right = generateExpression(root.right);
            if (compare(left, right) <= 0) {
                return root.getData() + left + right;
            } else {
                return root.getData() + right + left;
            }
        } else {
            return root.getData() + generateExpression(root.left) + generateExpression(root.right);
        }

    }

    public static void main(String[] args) {
        ExpTree tree = new ExpTree();
        tree.insert("-");
        tree.insert("5");
        tree.insert("+");

        tree.insert("8");
        tree.insert("4");
        System.out.println(tree.generateExpression());
        ExpTree tree3 = new ExpTree();
        tree3.insert("-");
        tree3.insert("5");
        tree3.insert("+");

        tree3.insert("4");
        tree3.insert("8");
        System.out.println(tree3.generateExpression());

        ExpTree tree2 = new ExpTree();
        tree2.insert("+");
        tree2.insert("5");
        tree2.insert("-");

        tree2.insert("8");
        tree2.insert("4");
        System.out.println(tree2.generateExpression());

        System.out.println(tree.inOrder());
        System.out.println(tree2.inOrder());
        System.out.println(tree.preOrder());
        System.out.println(tree.postOrder());
    }

}

class Node {
    Node left;
    Node right;
    String data;

    public Node(String data) {
        left = null;
        right = null;
        this.data = data;
    }

    public void setLeft(Node n) {
        left = n;
    }

    public void setRight(Node n) {
        right = n;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

