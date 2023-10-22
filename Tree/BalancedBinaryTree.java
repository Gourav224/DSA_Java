/*
 * Check if the Binary Tree is Balanced Binary Tree
 * Problem Statement: Check whether the given Binary Tree is a Balanced Binary Tree or not. A binary tree 
 * is balanced if, for all nodes in the tree, the difference between left and right subtree height is not more than 1.
 */

public class BalancedBinaryTree {
    public static boolean isBalanced(Node root) {
        return dfsHeight(root) != -1;
    }

    static int dfsHeight(Node root) {
        if (root == null)
            return 0;

        int leftHeight = dfsHeight(root.left);
        if (leftHeight == -1)
            return -1;
        int rightHeight = dfsHeight(root.right);
        if (rightHeight == -1)
            return -1;

        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        System.out.println("Is Balanced Binary Tree ? " +isBalanced(root));
    }
}
