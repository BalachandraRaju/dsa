package tree;
import tree.Util.*;
import static tree.Util.*;

public class BoundaryTraversal {

    public static void main(String[] args){
        BoundaryTraversal bt = new BoundaryTraversal();
        Node root = createTree();
        root.left.left.left = new Node(7);
        root.left.left.right = new Node(8);
        root.left.left.right.left = new Node(9);
        bt.traverse(root);

    }

    private void traverse(Node root) {
        printLeftTree(root);

        printLeaves(root.left);
        printLeaves(root.right);

        printRightTree(root);
    }

    private void printLeftTree(Node node){
        if(node == null)
            return;

        if(node.left != null){
            System.out.print(node.value+" ");
            printLeftTree(node.left);
        }else if(node.right !=null){
            System.out.print(node.value+" ");
            printLeftTree(node.right);
        }
        //don't print the leaves
    }

    private void printRightTree(Node node){
        if(node == null)
            return;
        if(node.right != null){
            printRightTree(node.right);
            System.out.print(node.value+" ");
        }else if(node.left != null){
            printRightTree(node.left);
            System.out.print(node.value+" ");
        }
    }

    private void printLeaves(Node node){
        if(node  == null)
            return;

        printLeaves(node.left);

        if(node.left == null && node.right == null){
            //found a leaf , print it
            System.out.print(node.value+" ");
        }
         if(node.right !=null)
            printLeaves(node.right);
    }


}
