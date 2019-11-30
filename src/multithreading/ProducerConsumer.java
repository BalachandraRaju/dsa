package multithreading;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumer {
    public static void main(String[] args){

        LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>(10);
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
    }

    static class Producer implements Runnable{

        BlockingDeque<Integer> queue;
        public  Producer(BlockingDeque<Integer> queue){
            this.queue =queue;
        }
        @Override
        public void run() {
            int i =1;
            while( i < 20){
                try {

                    int   k = (int) Math.random();
                    queue.put(i);
                    System.out.format("Produced : %d \n",i);
                    i++;
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    static class Consumer  implements Runnable{
        BlockingDeque<Integer> queue;

        public  Consumer(BlockingDeque<Integer> queue){
            this.queue =queue;
        }
        @Override
        public void run() {
            try {
                int i =1;
                while(i <20){
                    int number = queue.take();
                    System.out.format("Consumed : %d \n",number);
                    i++;
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
