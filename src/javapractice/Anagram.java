package javapractice;

public class Anagram {
    static int i = 5;
    static int k = getk();

    static {
        System.out.println("static variable");
    }

    char[] chStr;
    int a = 1;

    {
        System.out.println("instance variable");
    }

    public Anagram() {
        System.out.println("constructor called");
    }

    public Anagram(char[] chars) {
        this.chStr = chars;
    }

    private static int getk() {
        System.out.println("getk called");
        return k;
    }

    public static void main(String[] args) {
        Anagram anagram = new Anagram();
        anagram.chStr = "abcd".toCharArray();
        anagram.printAnagrams(anagram.chStr.length);
    }

     private void printAnagrams(int newSize) {
        if (newSize == 1)
            return;
        for (int i = 0; i < newSize; i++) {
            printAnagrams(newSize - 1);
            if (newSize == 2)
                System.out.println(new String(chStr));
            rotate(newSize);
        }
    }

    private void rotate(int newSize){
        int size = chStr.length;
        int pos = size - newSize;
        char temp = chStr[pos];
        int j;
        for (j = pos + 1; j < size; j++) {
                chStr[j-1] = chStr[j];
        }
        chStr[j-1] =temp;
    }



}

class A {
    Number a() throws Throwable {
        return 0;
    }
}

class B extends A {
    Integer a() throws RuntimeException {
        return 1;
    }
}
