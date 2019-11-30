package dev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountingSubsequence {

    public static void main(String[] args) throws Exception {
        CountingSubsequence countingSubsequence = new CountingSubsequence();
        countingSubsequence.test();
    }

    private void test() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);

        String S = br.readLine();
        int Q = Integer.parseInt(br.readLine().trim());

        String[] arr = new String[Q];
        for (int i = 0; i < Q; i++) {
            arr[i] = br.readLine().trim();
        }

        int[] out_ = findAllSequencesEndingWithArr(S, arr);
        System.out.print(out_[0]);
        for (int i = 1; i < out_.length; i++) {
            System.out.print(" " + out_[i]);
        }

        wr.close();
        br.close();
    }

    static int[] findAllSequencesEndingWithArr(String S, String[] arr) {
        int[] r = new int[arr.length];
        int i = 0;

        List<String> subSeqSet = new ArrayList<>();
        getAllSubsequences(S.toCharArray(), S.length(), -1, "", subSeqSet);
        //subSeqSet = getAllSubsequences(S);
        System.out.println(subSeqSet);

        for (String x : arr) {
            int count=0;
            for(String subSeq : subSeqSet){
                if(subSeq.endsWith(x)){
                    count++;
                }
            }
            r[i++] = count;
        }
        return r;
    }

    static void getAllSubsequences(char[] str, int n,
                                   int index, String curr, List<String> subSeqSet) {
        if (index == n)
            return;

        subSeqSet.add(curr);

        for (int i = index + 1; i < n; i++) {

            curr += str[i];
            getAllSubsequences(str, n, i, curr, subSeqSet);
            StringBuilder sb = new StringBuilder(curr);
            curr = sb.deleteCharAt(curr.length() - 1).toString();
        }
        return;
    }

    static Set<String> getAllSubsequences(String S) {
        Set<String> subseqSet = new HashSet<>();
        for (int i = 0; i < S.length(); i++) {
            for (int j = S.length(); j > i; j--) {
                String subStr = S.substring(i, j);
                if (!subseqSet.contains(subStr)) {
                    subseqSet.add(subStr);
                }

                for (int p = i + 1; p < j - 1; p++) {
                    StringBuffer sbStr = new StringBuffer(subStr);
                    sbStr.deleteCharAt(p);
                    String subSeq = sbStr.toString();
                    if (!subseqSet.contains(subSeq)) {
                        subseqSet.add(subSeq);
                    }
                }
            }
        }
        return subseqSet;
    }

}
