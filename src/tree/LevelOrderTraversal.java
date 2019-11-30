package tree;

import tree.Util.Node;

import static tree.Util.*;



public class LevelOrderTraversal {
    public static void main(String[] args){
        LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();
        Node root = createTree();
        levelOrderTraversal.traverse(root);
    }

     void traverse(Node root){
        int height = height(root);
        System.out.println("height = "+height);
        boolean ltr = true;
        for (int i = 1; i <= height; i++){
            printGivenLevel(root,i,ltr);
            System.out.println();
            //ltr = !ltr;
        }
    }

    private void printGivenLevel(Node node, int level,boolean ltr) {
        if(level == 1){
            System.out.print(node.value+" ");
        }else{
            if(ltr){
                if(node.left != null){
                    printGivenLevel(node.left,level-1,ltr);
                }
                if(node.right != null){
                    printGivenLevel(node.right,level-1,ltr);
                }
            }else{
                if(node.right != null){
                    printGivenLevel(node.right,level-1,ltr);
                }
                if(node.left != null){
                    printGivenLevel(node.left,level-1,ltr);
                }
            }
        }
    }
}
