/*
백준 온라인 저지 16434번 드래곤 앤 던전(Gold IV)
 */

import java.io.*;

public class BOJ_16434 {
    public static void main(String[] args) throws IOException {
        // 초기 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int n = Integer.parseInt(info[0]);
        long atk = Long.parseLong(info[1]);

        long curHP = 0, maxHP = 0;
        for (int i = 0; i < n; i++) {
            String[] room = br.readLine().split(" ");
            int N = Integer.parseInt(room[0]);
            int atkInfo = Integer.parseInt(room[1]);
            int HPInfo = Integer.parseInt(room[2]);

            if(N == 1) {
                // 몬스터의 방
                long turn = HPInfo / atk;
                if(HPInfo % atk != 0) turn++;
                curHP += (turn-1) * atkInfo;
                maxHP = (long)Math.max(curHP, maxHP);
            } else {
                // 포션의 방
                atk += atkInfo;
                curHP -= HPInfo;
                if(curHP < 0) curHP = 0;
            }
        }

        System.out.println(maxHP + 1);
    }
}