/*
 * Calculate the Diameter of a Binary Tree
 * Problem Statement: Find the Diameter of a Binary Tree. Diameter is the length of the longest path 
 * between any 2 nodes in the tree and this path may or may not pass from the root.
 */

public class Diameter {
    static int diameterOfBinaryTree(Node root) {
        int[] diameter = new int[1];
        height(root, diameter);
        return diameter[0];
    }

    static int height(Node node, int[] diameter) {
        if (node == null) {
            return 0;
        }
        int lh = height(node.left, diameter);
        int rh = height(node.right, diameter);
        diameter[0] = Math.max(diameter[0], lh + rh);
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
        System.out.println("Diameter of a Tree " +diameterOfBinaryTree(root));
    }
}
