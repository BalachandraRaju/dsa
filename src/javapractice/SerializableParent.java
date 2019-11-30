package javapractice;

import java.io.IOException;

public class SerializableParent {

    int j = 15;
    String Jstr = "fifteen";

    public static void main(String[] args) throws IOException {
        SerializableParent sep = new SerializableParent();
        sep.printCharCount("AABCCDA".toCharArray(),0);
    }

    private void printCharCount(char[] ch,int index){
       if(index >= ch.length)
           return;
       int pos = index+1;
       int count =1;
       while(pos < ch.length && ch[pos-1] == ch[pos]){
           count++;
           pos++;
       }
       System.out.format("%s:%d\t",ch[pos-1],count);
       printCharCount(ch,pos);
    }
}
