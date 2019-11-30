package javapractice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VMWareTest {

    public static void main(String[] ags) throws Exception{
        System.out.println("VMWare test");

        List<String>  dates = Arrays.asList("10 Jan 1985","10 Feb 2009","10 Jan 1982","10 March 1979");

        Collections.sort(dates, new Comparator<String>() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

            @Override
            public int compare(String o1, String o2) {
                try {
                    if(o1 == null || o2 == null)
                        return -1;
                    return sdf.parse(o1).compareTo(sdf.parse(o2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return -1;
            }
        });

        System.out.println(dates);
    }

    private final void a(){

    }

    private final void a(int a){

    }

    static class Some{

    }
}
