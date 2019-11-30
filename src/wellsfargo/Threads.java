package wellsfargo;

import java.util.concurrent.ExecutorService;

public class Threads {
    static Thread laurel , hardy;
    public static void main(String[] args){

        laurel =new Thread(){
            public void run(){
                System.out.println("A");
                    try {
                        hardy.sleep(1000);
                    } catch (Exception e) {
                        System.out.println("B");
                    }
                System.out.println("C");
            }
        };

        hardy =new Thread(){
            public void run(){
                System.out.println("D");
                try {
                    laurel.wait();
                } catch (Exception e) {
                    System.out.println("E");
                }
                System.out.println("f");
            }
        };

        //laurel.start();
        //hardy.start();

        Chess ch = new Chess();
        new Thread(ch).start();
        new Thread(new Chess()).start();

    }

    static class Chess implements  Runnable{
        public void run(){
            move(Thread.currentThread().getId());
        }

        private  void move(long id) {
            System.out.print(id+ " ");
            System.out.print(id+ " ");
        }
    }

    static class Dudes{
        static long flag = 0;

          synchronized  void chat(long id) {
            if (flag == 0) {
                flag = id;
            }
            for(int x = 1;x<3;x++){
                if(flag == id ){
                    System.out.println("yo");
                }else{
                    System.out.println("dude");
                }
            }
        }
    }

    static class DudesChat implements Runnable{

        static Dudes d;
        public static void main(String[] args){
            new DudesChat().go();
        }
         void go(){
            d = new Dudes();
            new Thread(new DudesChat()).start();
            new Thread(new DudesChat()).start();
        }
        public void run(){
            d.chat(Thread.currentThread().getId());
        }
    }


    static class WaitTest {
        public static void main(String[] args){
            System.out.println("1");

            synchronized (args){
                System.out.println("2");
                try{
                    args.wait();
                }catch(InterruptedException e){

                }
                System.out.println("3");
            }
        }
    }


}
