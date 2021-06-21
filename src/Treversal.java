import java.util.ArrayList;
import java.util.Stack;

public class Treversal {


    int currentRootIndex = 0;
    Stack<Pair> stack = new Stack<Pair>();
    ArrayList<String> postorderTraversal = new ArrayList<String>();
    ArrayList<String> preorderTraversal = new ArrayList<String>();
    // Function to perform iterative postorder traversal
    public ArrayList<String> postorder(Node root) {
        while (root != null || !stack.isEmpty()) {
            if (root != null) {

                // Push the root and it's index
                // into the stack
                stack.push(new Pair(root, currentRootIndex));
                currentRootIndex = 0;

                // If root don't have any children's that
                // means we are already at the left most
                // node, so we will mark root as null
                if (root.children.size() >= 1) {
                    root = root.children.get(0);
                } else {
                    root = null;
                }
                continue;
            }

            // We will pop the top of the stack and
            // add it to our answer
            Pair temp = stack.pop();
            postorderTraversal.add(temp.node.data);

            // Repeatedly we will the pop all the
            // elements from the stack till popped
            // element is last children of top of
            // the stack
            while (!stack.isEmpty() && temp.childrenIndex ==
                    stack.peek().node.children.size() - 1) {
                temp = stack.pop();

                postorderTraversal.add(temp.node.data);
            }

            // If stack is not empty, then simply assign
            // the root to the next children of top
            // of stack's node
            if (!stack.isEmpty()) {
                root = stack.peek().node.children.get(
                        temp.childrenIndex + 1);
                currentRootIndex = temp.childrenIndex + 1;
            }
        }

        return postorderTraversal;
    }

    public ArrayList<String> preorder(Node root) {
        Stack<Node> stackNode = new Stack<>();
        stackNode.push(root);
        while (!stackNode.isEmpty()) {
            // Store the current node and pop
            // it from the stack
            Node curr = stackNode.pop();

            // Current node has been travarsed
            if (curr != null)
            {
                //System.out.print(curr.data + " ");
                preorderTraversal.add(curr.data);
                // Store all the childrent of
                // current node from right to left.
                for(int i = curr.children.size() - 1; i >= 0; i--)
                {
                    stackNode.add(curr.children.get(i));
                }
            }
        }
        return preorderTraversal;
    }
}
