/*
백준 온라인 저지 1715번 카드 정렬하기(Gold IV)
 */

import java.io.*;
import java.util.*;

public class BOJ_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        PriorityQueue<Long> cards =  new PriorityQueue<>();
        while(t-- > 0) {
            cards.add(Long.parseLong(br.readLine()));
        }

        long sum = 0, temp1, temp2;
        while(cards.size() > 1) {
            temp1 = cards.poll();
            temp2 = cards.poll();
            sum += temp1 + temp2;
            cards.add(temp1+temp2);
        }

        System.out.println(sum);
    }
}