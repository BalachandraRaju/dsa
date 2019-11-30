package linkedlist;

import static linkedlist.ListUtil.*;

public class Reverse {

    public static void main(String[] args) {
        Node root = createLinkedList();
        printLinkedList(root);
        root = reverseRec(root, null);
        printLinkedList(root);
    }


    private static Node reverse(Node root) {
        Node curr = root;
        Node prev = null;
        while (curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    private static Node reverseRec(Node curr, Node prev) {
        if (curr == null) {
            return prev;
        }
        Node next = curr.next;
        curr.next = prev;
        return reverseRec(next, curr);
    }

}
