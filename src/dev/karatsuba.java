package dev;

import java.math.BigInteger;
import java.util.Scanner;

import static java.math.BigInteger.TEN;

public class karatsuba {


    public void main(String args) {
        new karatsuba().multiply();
    }

    private void multiply() {
        Scanner scanner = new Scanner(System.in);
        String m = scanner.nextLine();
        String n = scanner.nextLine();

        BigInteger lm = new BigInteger(m);
        BigInteger ln = new BigInteger(n);

        BigInteger result = multiplyUtil(lm, ln);
        System.out.println(result);
    }

    private BigInteger multiplyUtil(BigInteger m, BigInteger n) {
        int lm = m.toString().length();
        int ln = n.toString().length();

        if (lm <= 2 || ln <= 2) {
            return m.multiply(n);
        }

        int indexA = lm / 2;


        BigInteger a = m.divide(TEN.pow(indexA));//new BigInteger(m.toString().substring(0,indexA));
        BigInteger b = m.subtract(a.multiply(TEN.pow(indexA)));//new BigInteger(m.toString().substring(indexA,lm));

        BigInteger c = n.divide(TEN.pow(indexA));//new BigInteger(n.toString().substring(0,indexA));
        BigInteger d = n.subtract(c.multiply(TEN.pow(indexA)));//new BigInteger(n.toString().substring(indexA,ln));

        BigInteger ac = multiplyUtil(a, c);
        BigInteger bd = multiplyUtil(b, d);
        BigInteger abcd = multiplyUtil(a.add(b), c.add(d));
        BigInteger adbc = abcd.subtract(ac).subtract(bd);

        //System.out.printf("a=%d\tb=%d\tc=%d\td=%d\t\n",a,b,c,d);
        //System.out.printf("ac=%d\tbd=%d\tabcd=%d\tadbc=%d\t\n",ac,bd,abcd,adbc);

        BigInteger result = ac.multiply(TEN.pow(2 * indexA))
                .add(adbc.multiply(BigInteger.TEN.pow(indexA)))
                .add(bd);

        return result;
    }


    interface A {
        int add(int a, int b);
    }

    interface B extends A {
        int add(double a, double b);
    }

}
