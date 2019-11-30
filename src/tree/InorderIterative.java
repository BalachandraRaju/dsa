package tree;

import static tree.Util.*;
import tree.Util.*;

import java.util.Stack;

public class InorderIterative {

    public static void main(String[] args){
        Node tree = createTree();
        InorderIterative it = new InorderIterative();
        System.out.println("Recursive: ");
        it.recTraverse(tree);
        System.out.println("\nIterative: ");
        it.traverse(tree);
    }
    private void traverse(Node root){
        Node curr = root;
        Stack<Node> stack = new Stack<>();

        while(curr != null || !stack.isEmpty()){

            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            System.out.print(curr.value+" ");

            curr = curr.right;
        }
    }

    private void recTraverse(Node root){
        if(root == null)
            return;

        recTraverse(root.left);
        System.out.print(root.value+" ");
        recTraverse(root.right);
    }
}
