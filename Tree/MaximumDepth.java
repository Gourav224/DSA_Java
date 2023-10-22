/*
 * Maximum depth of a Binary Tree
 * Problem Statement: Find the Maximum Depth of Binary Tree. Maximum Depth is the count of nodes of the 
 * longest path from the root node to the leaf node.

 
 */

public class MaximumDepth {
    public static int maxDepth(Node root) {
        if (root == null)
            return 0;

        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);

        return 1 + Math.max(lh, rh);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        System.out.println("Height of Tree is " + maxDepth(root));
    }
}
