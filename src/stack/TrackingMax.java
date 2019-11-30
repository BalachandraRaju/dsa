package stack;

import java.util.Stack;

public class TrackingMax {

    Stack<Integer> main = new Stack<>();
    Stack<Integer> track = new Stack<>();

    public static void main(String[] args){
        TrackingMax trackingMax = new TrackingMax();
        trackingMax.push(10);
        System.out.println("Max = "+trackingMax.getMax());

        trackingMax.push(20);
        System.out.println("Max = "+trackingMax.getMax());
        trackingMax.push(0);
        System.out.println("Max = "+trackingMax.getMax());
        trackingMax.push(9);
        System.out.println("Max = "+trackingMax.getMax());
        trackingMax.push(15);
        System.out.println("Max = "+trackingMax.getMax());
        trackingMax.push(18);
        System.out.println("Max = "+trackingMax.getMax());

        trackingMax.push(30);
        System.out.println("Max = "+trackingMax.getMax());

        trackingMax.pop();
        System.out.println("Max = "+trackingMax.getMax());

        trackingMax.pop();
        System.out.println("Max = "+trackingMax.getMax());
    }

    private void push(int elem){
        main.push(elem);
        if(track.isEmpty()){
            track.push(elem);
            return;
        }

        if(elem > track.peek()){
            track.push(elem);
        }else{
            track.push(track.peek());
        }
    }

    private int getMax(){
        return track.peek();
    }

    private int pop(){
        track.pop();
        return main.pop();
    }
}
