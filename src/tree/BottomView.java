package tree;

import tree.Util.Node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import static tree.Util.*;

public class BottomView {

    public static void main(String[] args){
        BottomView bottomView = new BottomView();
        Node root = createTree();
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(8);
        bottomView.printNodes(root);
    }

    private void printNodes(Node root){
        Deque<Node> queue = new LinkedList<>();
        Map<Integer,Node> map = new TreeMap<>();
        queue.addLast(root);

        while(!queue.isEmpty()){
            Node node = queue.removeFirst();
            map.put(node.hd,node);

            if(node.left != null){
                node.left.hd = node.hd -1;
                queue.addLast(node.left);
            }
            if(node.right != null){
                node.right.hd = node.hd+1;
                queue.addLast(node.right);
            }
        }

        for(Map.Entry<Integer,Node> entry : map.entrySet()){
            System.out.format("%s ",entry.getValue());
        }
    }
}
