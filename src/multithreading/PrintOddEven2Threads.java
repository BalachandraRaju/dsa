package multithreading;

public class PrintOddEven2Threads {


    public static void main(String[] args){

        OddEvenPrinter printer = new OddEvenPrinter();

        Thread even = new Thread(new OddEven(printer,true));
        Thread odd = new Thread(new OddEven(printer,false));

        even.start();
        odd.start();
    }

    static class OddEven implements  Runnable{

        OddEvenPrinter printer;
        boolean isEven;
        int max =20;

        public OddEven(OddEvenPrinter printer,boolean isEven){
            this.printer = printer;
            this.isEven = isEven;
            this.max = max;
        }
        @Override
        public void run() {

            int number = isEven ? 0 : 1;
            while(number <= max){
                if(isEven){
                    printer.printEven(number);
                }else{
                    printer.printOdd(number);
                }
                number+=2;
            }
        }
    }
    static class OddEvenPrinter {

        boolean hasOddPrinted = true;

         synchronized  void printEven(int even){
            while(!hasOddPrinted){
                try{
                    wait();
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
            System.out.format("Even: %d\n",even);
            hasOddPrinted = false;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notify();
        }

         synchronized  void printOdd(int odd){
            while(hasOddPrinted){
                try{
                    wait();
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
            System.out.format("Odd: %d\n",odd);
            hasOddPrinted = true;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notify();
        }
    }
}
