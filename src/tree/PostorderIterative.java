package tree;
import static tree.Util.*;
import tree.Util.Node;

import java.util.Stack;

public class PostorderIterative {

    public static void main(String[] args){
        PostorderIterative poi = new PostorderIterative();
        Node root = createTree();
        System.out.println("Recursive: ");
        poi.recTraverse(root);
        System.out.println("\nIterative: ");
        poi.traverse(root);
    }

    private void recTraverse(Node root) {
        if(root == null)
            return;
        recTraverse(root.left);
        recTraverse(root.right);
        System.out.print(root.value+" ");
    }

    private void traverse(Node root){
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.push(root);

        while(!stack1.isEmpty()){
            Node node = stack1.pop();
            stack2.push(node);

            if(node.left != null)
                stack1.push(node.left);
            if(node.right != null)
                stack1.push(node.right);
        }

        while(!stack2.isEmpty()){
            System.out.print(stack2.pop().value+" ");
        }
    }
}
