package tree;
import static tree.Util.*;
import tree.Util.Node;

import java.util.*;


public class VerticalOrderTraversalTopView {

    public static void main(String[] args){
        VerticalOrderTraversalTopView levelOrder = new VerticalOrderTraversalTopView();
        Node root = createTree();
        levelOrder.traverse(root);
        levelOrder.topView(root);
    }

    private void topView(Node root) {
        System.out.println("********************TOP VIEW*********************");
        Deque<Node> deque = new LinkedList<>();
        root.hd =0;
        deque.addLast(root);

        Map<Integer,Node> map = new TreeMap<>();
        while(!deque.isEmpty()){
            Node node = deque.removeFirst();
            if(!map.containsKey(node.hd)){
                map.put(node.hd,node);
            }

            if(node.left != null){
                node.left.hd = node.hd-1;
                deque.add(node.left);
            }
            if(node.right != null){
                node.right.hd = node.hd+1;
                deque.add(node.right);
            }
        }

        for(Map.Entry<Integer,Node> entry: map.entrySet()){
            System.out.format("hd:%d\tvalue:%s\n",entry.getKey(),entry.getValue());
        }
    }



    private void traverse(Node root) {
        Deque<Node> deque = new LinkedList<>();
        root.hd =0;
        deque.addLast(root);

        Map<Integer,List<Node>> map = new TreeMap<>();
        while(!deque.isEmpty()){
            Node node = deque.removeFirst();
            if(!map.containsKey(node.hd)){
                List<Node> list = new LinkedList<>();
                list.add(node);
                map.put(node.hd,list);
            }else{
                map.get(node.hd).add(node);
            }

            if(node.left != null){
                node.left.hd = node.hd-1;
                deque.add(node.left);
            }
            if(node.right != null){
                node.right.hd = node.hd+1;
                deque.add(node.right);
            }
        }

        for(Map.Entry<Integer,List<Node>> entry: map.entrySet()){
            System.out.format("hd:%d\tvalues:%s\n",entry.getKey(),entry.getValue());
        }
    }
}
