package tree;

public class Util {

   public static class Node{
        Node left;
        Node right;
        int value;
        int hd;

        Node(int value){
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

     public static Node createTree() {
        Node root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(2);

        root.left.left = new Node(3);
        root.left.right = new Node(4);

        root.right.left = new Node(5);
        root.right.right = new Node(6);

        return root;
    }

    static int height(Node root){
        if(root == null)
            return 0;

        int left = height(root.left);
        int right = height(root.right);

        if(left > right)
            return left+1;
        else
            return right+1;
    }
}
