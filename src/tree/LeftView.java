package tree;

import tree.Util.Node;

import java.util.Deque;
import java.util.LinkedList;

import static tree.Util.createTree;
import static tree.Util.height;

public class LeftView {

    public static void main(String[] args) {
        LeftView leftView = new LeftView();
        Node root = createTree();
        root.left.left.right = new Node(7);
        root.left.right.left = new Node(8);
        root.left.right.right = new Node(9);
        root.left.right.right.right = new Node(10);
        root.right.right.right = new Node(11);
        root.right.right.right.left = new Node(12);
        root.right.right.right.left.left = new Node(13);
        new LevelOrderTraversal().traverse(root);
        System.out.println("*******************");
        leftView.traverse(root);
    }

    private void traverse(Node root) {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            Deque<Node> queue = new LinkedList<>();
            getLeftMostNodeAtGivenLevel(root, i,queue);
            System.out.print(queue.removeFirst() + " ");
        }
    }

    private void getLeftMostNodeAtGivenLevel(Node root, int level,Deque<Node> queue) {
        if (level == 1) {
            queue.addLast(root);
        }else{
            if(root.left != null){
                 getLeftMostNodeAtGivenLevel(root.left,level-1,queue);
            } if(root.right != null){
                 getLeftMostNodeAtGivenLevel(root.right,level-1,queue);
            }
        }
    }
}
