package dev;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPair {

    /**
     * Point denotes a point in a plane with x and y coordinates
     * It also implements Comparable so that it could be sorted by List interfaces.
     * It has a property compareBy based on which it is sorted by x coordinate or y coordinate.
     */
    static class Point  {
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Point() {

        }

        @Override
        public String toString() {
            return String.format("[x=%d, y=%d]",x,y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    static class CompareByX implements Comparator<Point>{

        @Override
        public int compare(Point p, Point q) {
            if(p == null || q == null){
               throw new IllegalArgumentException("Point p or q cannot be null");
            }
            return p.x-q.x;
        }
    }

    static class CompareByY implements Comparator<Point>{

        @Override
        public int compare(Point p, Point q) {
            if(p == null || q == null){
                throw new IllegalArgumentException("Point p or q cannot be null");
            }
            return p.y-q.y;
        }
    }

    static class Pair {
        Point p;
        Point q;
        double d;

        public Pair(Point p, Point q,double d){
            this.p = p;
            this.q= q;
            this.d = d;
        }

        public Pair() {

        }

        @Override
        public String toString() {
            return String.format("X=%s\tY=%s\tED=%f",p,q,d);
        }
    }

    private double  getEuclideanDist(Point x, Point y){
        double xsq = Math.pow(x.x-y.x,2);
        double ysq = Math.pow(x.y-y.y,2);
        return  Math.sqrt(xsq+ysq);
    }

    private Pair  bruteForce(Point[] points){
        System.out.println("Points length="+points.length);

        double min= Double.MAX_VALUE;
        Pair p = new Pair();
        p.d = Float.MAX_VALUE;
        for(int i=0; i< points.length-2;i++){
            for(int j=i+1;j<points.length;j++){
                double d = getEuclideanDist(points[i],points[j]);
                if(d < min){
                    min = d;
                    p.p= points[i];
                    p.q= points[j];
                    p.d = d;
                }
            }
        }
        System.out.println("BruteForce:"+p);

        return p;
    }


    private Pair closestPair(Point[] P){
        if(P.length <=3){
            return bruteForce(P);
        }

        Point[] X = Arrays.copyOf(P,P.length);
        Point[] Y = Arrays.copyOf(P,P.length);

        Arrays.sort(X,new CompareByX());
        Arrays.sort(Y,new CompareByY());

        Pair closestPair = closestPairUtil(X,Y,P.length,"");
        return closestPair;
    }
    private Pair closestPairUtil(Point[] X, Point[] Y, int length,String str) {
        System.out.print(String.format("%25s%20s\n","START"+str,"").replaceAll(" ","-"));
        System.out.printf("Xlen=%d,Ylen=%d,length=%d\n",X.length,Y.length,length);

        System.out.println("X:"+Arrays.toString(X));
        System.out.println("Y:"+Arrays.toString(Y));
        if(length <=3){
            Pair p =  bruteForce(X);
        System.out.print(String.format("%38s%7s\n","END BRUTEFORCE","").replaceAll(" ","-"));
            return p;
        }
        int mid = length /2;
        Point midPoint = X[mid-1];

        List<Point> Xl = new ArrayList<>();
        List<Point> Xr = new ArrayList<>();

        for(int i=0;i<X.length;i++){
            if(X[i].x <= midPoint.x){
                Xl.add(X[i]);
            }else{
                Xr.add(X[i]);
            }
        }

        System.out.println("Xl:"+Xl);
        System.out.println("Xr:"+Xr);


        List<Point> Yl = new ArrayList<>();
        List<Point> Yr = new ArrayList<>();

        for(int i=0;i<Y.length;i++){
            if(Y[i].x <= midPoint.x){
                Yl.add(Y[i]);
            }else{
                Yr.add(Y[i]);
            }
        }
        System.out.println("Yl:"+Yl);
        System.out.println("Yr:"+Yr);

        Pair left =  closestPairUtil(Xl.toArray(new Point[0]),Yl.toArray(new Point[0]),mid,"LEFT");
        Pair right = closestPairUtil(Xr.toArray(new Point[0]),Yr.toArray(new Point[0]),length-mid,"RIGHT");
        System.out.println("Left="+left);
        System.out.println("Right="+right);
        Pair closest;
        if(left.d < right.d){
            closest = left;
        }else{
            closest = right;
        }
        System.out.println("Closest="+closest);
        Pair closestSplitPair = closestSplitPair(Y,midPoint,closest.d);
        System.out.println("---------END CLOSESPLIT="+closestSplitPair+"--------");
        return  closestSplitPair == null ? closest: closestSplitPair;
    }

    private Pair closestSplitPair(Point[] y, Point midpoint, double closestED) {
        List<Point> Sy = new ArrayList<>();
        double d = closestED;
        for(int i=0;i<y.length;i++){
            int yx = y[i].x;
            if(Math.abs(yx-midpoint.x) <= d ){
                Sy.add(y[i]);
            }
        }
        System.out.println("Midpoint="+midpoint);
        System.out.println("Sy="+Sy);

        double min = closestED;
        Pair p = null;
        for(int i=0;i<Sy.size();i++){
            int jsize = Math.min(7,Sy.size());
            for(int j=i+1;j<jsize;j++){
                System.out.printf("i=%d\tj=%d\tjsize=%d\n",i,j,jsize);
                double ed = getEuclideanDist(Sy.get(i),Sy.get(j));
                if(ed < min){
                    min = ed;
                    p = new Pair(Sy.get(i),Sy.get(j),min);
                }
            }
        }
        return p;
    }

    public static void main(String[] args){
        Point[] p = {
                new Point(503834001,-192757686),
                new Point(-753501882,-220105607),
                new Point(-767060345,-447191517),
                new Point(563736249,-865331455),
                new Point(-780938209,372190004),
                new Point(100907440,534669871),
                //new Point(12,10),
                //new Point(3,4)
            };
        Pair closestPair = new ClosestPair().closestPair(p);
        System.out.print("Final:::"+closestPair);
    }
}
