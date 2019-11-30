package javapractice;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TestThermo {

    public static void main(String[] args){

        System.out.println(String.format("id = %08.2f",423.147));
        TestThermo testThermo = new TestThermo();
        A a = new B();
        B b = new B();
        a = b;

        Map<Integer,String> map = new LinkedHashMap<>();
        map.put(1,"one");
        map.put(2,"two");
        map.put(3,"three");
        map.put(4,"abc");

        map = map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(e-> 2, Map.Entry::getValue, String::concat,LinkedHashMap::new));
        System.out.println(map);
    }




   static class A{
        int  i =10;
    }

   static  class B extends A{
         int i =20;

        public  void l(){
            System.out.println(super.i);
        }
    }
}
