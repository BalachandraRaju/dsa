package linkedlist;

import java.util.stream.Stream;

public class ListUtil {

    public static Node createLinkedList() {
        Node[] nodes = Stream.iterate(1, i -> i + 1).limit(10)
                .map(Node::new).toArray(Node[]::new);
        for (int i = 1; i < nodes.length; i++) {
            nodes[i - 1].next = nodes[i];
        }
        return nodes[0];
    }

    public static void printLinkedList(Node root) {
        Node curr = root;
        while (curr != null) {
            System.out.format("%s -> ", curr);
            curr = curr.next;
        }
        System.out.println("null");
    }

    static class Node {
        Node next;
        int data;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }
}
