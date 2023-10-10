import java.util.*;

public class IterativeTraversals {
    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(8);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(10);

        ArrayList<Integer> preOrder = new ArrayList<>();
        preOrder = preOrderTrav(root);

        System.out.println("The preOrder Traversal is : ");
        for (int i = 0; i < preOrder.size(); i++) {
            System.out.print(preOrder.get(i) + " ");
        }

        System.out.println();

        ArrayList<Integer> inOrder;
        inOrder = inOrderTrav(root);

        System.out.println("The inOrder Traversal is : ");
        for (int i = 0; i < inOrder.size(); i++) {
            System.out.print(inOrder.get(i) + " ");
        }

        System.out.println();

        ArrayList<Integer> postOrder = new ArrayList<>();
        postOrder = postOrderTrav(root);

        System.out.println("The postOrder Traversal is : ");
        for (int i = 0; i < postOrder.size(); i++) {
            System.out.print(postOrder.get(i) + " ");
        }

    }

    static ArrayList<Integer> preOrderTrav(Node curr) {
        ArrayList<Integer> preOrder = new ArrayList<Integer>();
        if (curr == null)
            return preOrder;

        Stack<Node> s = new Stack<>();
        s.push(curr);

        while (!s.isEmpty()) {
            Node topNode = s.peek();
            preOrder.add(topNode.data);
            s.pop();
            if (topNode.right != null)
                s.push(topNode.right);
            if (topNode.left != null)
                s.push(topNode.left);
        }
        return preOrder;

    }

    static ArrayList<Integer> inOrderTrav(Node curr) {
        ArrayList<Integer> inOrder = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        while (true) {
            if (curr != null) {
                s.push(curr);
                curr = curr.left;
            } else {
                if (s.isEmpty())
                    break;
                curr = s.peek();
                inOrder.add(curr.data);
                s.pop();
                curr = curr.right;
            }
        }
        return inOrder;
    }

    static ArrayList<Integer> postOrderTrav(Node cur) {

        ArrayList<Integer> postOrder = new ArrayList<>();
        if (cur == null)
            return postOrder;

        Stack<Node> st = new Stack<>();
        while (cur != null || !st.isEmpty()) {

            if (cur != null) {
                st.push(cur);
                cur = cur.left;
            } else {
                Node temp = st.peek().right;
                if (temp == null) {
                    temp = st.peek();
                    st.pop();
                    postOrder.add(temp.data);
                    while (!st.isEmpty() && temp == st.peek().right) {
                        temp = st.peek();
                        st.pop();
                        postOrder.add(temp.data);
                    }
                } else
                    cur = temp;
            }
        }
        return postOrder;

    }
}
