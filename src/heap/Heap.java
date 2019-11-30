package heap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Heap<K extends Comparable<K>, T> {

    List<Node> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        new Heap<>().medianMaintenance();
    }

    private void test() {
        Heap<Integer, String> heap = new Heap<>();
        heap.insert(-4, "Four");
        heap.insert(-3, "Three");
        heap.insert(-2, "Two");
        heap.insert(-1, "One");
        System.out.println("Inserted: " + heap.list);


        System.out.println("Extract " + heap.extractMin());
        System.out.println("Remaining:" + heap.list);
        System.out.println("Extract " + heap.extractMin());
        System.out.println("Remaining:" + heap.list);
        System.out.println("Extract " + heap.extractMin());
        System.out.println("Remaining:" + heap.list);
        System.out.println("Extract " + heap.extractMin());
        System.out.println("Remaining:" + heap.list);
    }

    private void medianMaintenance() throws Exception {

        Heap<Integer, Integer> maxHeap = new Heap<>();
        Heap<Integer, Integer> minHeap = new Heap<>();

        String fileName = "C:\\Users\\Bharath\\IdeaProjects\\Developement\\ModuleOne\\src\\heap\\Median.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName)));
        String line;
        int sum = 0;
        while ((line = br.readLine()) != null) {
            Integer n = Integer.parseInt(line);
            if (maxHeap.isEmpty()) {
                if (!minHeap.isEmpty() && n > minHeap.findMin()) {
                    minHeap.insert(n, n);
                }else{
                    maxHeap.insert(-n, n);
                }
            } else if (minHeap.isEmpty()) {
                if(!maxHeap.isEmpty() && n < maxHeap.findMin()){
                    maxHeap.insert(-n,n);
                }else{
                    minHeap.insert(n, n);
                }
            } else {
                // both heaps not empty
                int x = maxHeap.findMin();

                if (n < x) {
                    // number is less than max of maxHeap, hence in order to hold the variant
                    // n should go to maxHeap so that all elements from maxHeap are less than all elems from minHeap
                    maxHeap.insert(-n, n);
                } else  {
                    // if (n > y)
                    //number is greater than min of minHeap, hence it should go to minHeap
                    //this is to hold the invariant that all elements in maxHeap should be less than
                    // all the elements in minHeap
                    //OR if n < y && n > x --> x < n < y (10 < 15 < 20)
                    minHeap.insert(n,n);
                }
            }
            validateAndBalanceHeaps(maxHeap, minHeap);

            int minHeapSize = minHeap.size();
            int maxHeapSize = maxHeap.size();
            int total = minHeapSize + maxHeapSize;

            int median;
            if( (total & 1) == 0 ){
                median = maxHeap.findMin();
            }else{
                median =  minHeapSize > maxHeapSize ? minHeap.findMin() : maxHeap.findMin();
            }
            sum+=median;
            /*System.out.format("Median for count=%d\tmaxH=%s\tminH=%s is: %d\n",
                    total,maxHeap,minHeap,median);*/
        }
        System.out.println("Result: "+(sum%10000));

    }

    @Override
    public String toString() {
        return list.toString();
    }

    private void validateAndBalanceHeaps(Heap<Integer, Integer> maxHeap, Heap<Integer, Integer> minHeap) {
        int minHeapSize = minHeap.size();
        int maxHeapSize = maxHeap.size();
        int total = minHeapSize + maxHeapSize;
        if( (total & 1) == 0 && minHeapSize != maxHeapSize){
            //if total number of elements are even and both heap sizes are not equal i.e not balanced
            // we need to extract element from bigger size heap and insert into smaller size heap
            if(maxHeapSize > minHeapSize){
                int data = maxHeap.extractMin();
                minHeap.insert(data,data);
            }else{
                int data = minHeap.extractMin();
                maxHeap.insert(-data,data);
            }
            if(maxHeap.size() != minHeap.size()){
                throw new IllegalStateException("Heaps could not be balanced for even number of elements even after balancing the heaps");
            }
        }

    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void insert(K key, T data) {
        Node newNode = new Node(key, data);
        list.add(newNode);

        if (list.size() == 1) {
            return;
        }

        int current = list.size() - 1;
        int parent = (current - 1) / 2;

        Node parentNode = list.get(parent);
        while (current > 0 && parentNode.key.compareTo(key) > 0) {
            list.set(parent, newNode);
            list.set(current, parentNode);
            current = parent;
            parent = (parent - 1) / 2;
            parentNode = list.get(parent);
        }
    }

    public T extractMin() {
        int size = list.size();

        if (size == 1) {
            return list.remove(0).data;
        }
        int current = 0;//root
        int last = size - 1; //last element

        Node rootNode = list.get(current);
        Node lastNode = list.remove(last);

        list.set(current, lastNode); // set root to last node
        Node topNode = list.get(current);

        int left = 2 * current + 1;
        int right = 2 * current + 2;

        int currentSize = list.size();
        int smallerIndex = 0;
        while (left < currentSize) {
            Node leftNode = list.get(left);
            if (right < currentSize && list.get(right).key.compareTo(leftNode.key) < 0) {
                smallerIndex = right;
            } else {
                smallerIndex = left;
            }
            if (topNode.key.compareTo(list.get(smallerIndex).key) < 0) {
                //current node is smaller than the smallest of left / right
                //hence heap condition is satisfied.
                smallerIndex = current;
                break;
            }
            //copy the smallest to the upper level
            list.set(current, list.get(smallerIndex));
            current = smallerIndex;
            left = 2 * current + 1;
            right = 2 * current + 2;
        }
        list.set(smallerIndex, topNode);
        return rootNode.data;
    }

    public int size(){
        return list.size();
    }

    private Object extractMin2() {
        int size = list.size();

        if (size == 0) {
            System.err.println("No elements found in heap, returning null");
            return null;
        }
        if (size == 1)
            return list.remove(0);

        int i = 0; // root
        Node returnVal = list.get(i); // extract the root node
        list.set(i, list.get(size - 1)); // set the root to last element
        list.remove(size - 1); //remove the last node
        Node root = list.get(i); // get the updated root.
        size = list.size();  //update the size

        while (i < size) {

            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;

            if (leftIndex >= size)
                // this enough to break, because leftIndex is always less than rightIndex
                break;

            //here , leftIndex < size
            Node left = list.get(leftIndex);
            K rootKey = root.key;
            K leftKey = left.key;

            Node swapNode;
            int swapIndex;
            if (rightIndex < size) {
                //leftIndex,rightIndex both are within size limit
                Node right = list.get(rightIndex);
                K rightKey = right.key;

                if (leftKey.compareTo(rootKey) > 0 && rightKey.compareTo(rootKey) > 0) {
                    //both left and right nodes of the rootKey are greater than rootKey, hence heap condition satisfied,break
                    break;
                }
                swapNode = leftKey.compareTo(rightKey) < 0 ? left : right;
                swapIndex = swapNode == left ? leftIndex : rightIndex;

            } else {
                //leftIndex < size and rightIndex > size , hence there is only one left child node.
                if (leftKey.compareTo(rootKey) > 0) {
                    break; //heap condition satisfied.
                }
                swapNode = left;
                swapIndex = leftIndex;
            }
            //set the min index to root
            list.set(swapIndex, root);
            //set the root index to min
            list.set(i, swapNode);
            //update the new root index to swapIndex
            i = swapIndex;
        }
        return returnVal;
    }

    public T findMin() {
        if (list.size() == 0) {
            System.out.println("No elements found in the heap!!");
            return null;
        }
        return list.get(0).data;
    }

    public T delete(T data) {
        if (list.size() == 0) {
            System.out.println("No elements found in the heap to delete !!");
            return null;
        }

        int current = 0;
        int size = list.size();

        Node last = list.get(size - 1);

        while (current < size / 2) {
            int left = 2 * current + 1;
            int right = 2 * current + 2;
            int parent = (current - 1) / 2;

            Node currentNode = list.get(current);
            if (currentNode.data == data) {
                break;
            }
        }
        return null;
    }

    public T find(T data) {
        if (list.isEmpty()) {
            System.out.println("No elements in the heap!!");
            return null;
        }

        Node node = findUtil(list.get(0), data, 0, list.size());
        return node.data;
    }

    private Node findUtil(Node node, T data, int current, int size) {
        if (node == null)
            return null;

        if (node.data == data) {
            return node;
        }

        int left = 2 * current + 1;
        int right = 2 * current + 2;

        Node retVal = null;
        if (left < size) {
            retVal = findUtil(list.get(left), data, left, size);
        }
        if (retVal == null && right < size) {
            retVal = findUtil(list.get(right), data, right, size);
        }

        return retVal;
    }

    class Node {
        T data;
        K key;

        public Node(K key, T data) {
            this.data = data;
            this.key = key;
        }

        @Override
        public String toString() {
            return String.format("%s:%s", key, data);
        }
    }
}
