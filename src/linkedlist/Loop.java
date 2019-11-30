package linkedlist;

import static linkedlist.ListUtil.Node;
import static linkedlist.ListUtil.createLinkedList;

public class Loop {

    public static void main(String[] args) {
        Node root = createLinkedList();
        root.next.next = root;
        //printLinkedList(root);
        System.out.println("Loop : " + isLoop(root));
    }

    private static boolean isLoop(Node root) {

        if (root == null || root.next == null) {
            return false;
        }
        Node slow = root;
        Node fast = root;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == null) {
                return false;
            }
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
