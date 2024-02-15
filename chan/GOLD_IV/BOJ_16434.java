package chan.GOLD_IV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/16434
public class BOJ_16434 {
    public static void main (String[] args) throws IOException {
        DandD();
    }

    private static void DandD() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Atk = Integer.parseInt(st.nextToken());

        long start = 1;
        long end = 0;

        int [][] rooms = new int [N][3];
        for (int i = 0; i < N; i++){
            StringTokenizer rt = new StringTokenizer(br.readLine());
            rooms[i][0] = Integer.parseInt(rt.nextToken());
            rooms[i][1] = Integer.parseInt(rt.nextToken());
            rooms[i][2] = Integer.parseInt(rt.nextToken());
            end += (long) rooms[i][2] * rooms[i][1];
        }

        while (start <= end){
            long mid = (start + end) / 2;
            long humanCurHP = mid;
            long humanAtk = Atk;
            boolean result = true;

            for (int i = 0; i < N; i++){
                int t = rooms[i][0];
                int tATK = rooms[i][1];
                int tHP = rooms[i][2];

                if (t == 1){ //몬스터
                    int rounds = 0;
                    if (tHP % humanAtk == 0){
                        rounds = (int) ((tHP / humanAtk) - 1);
                    }else {
                        rounds = (int) ((tHP / humanAtk));
                    }
                    long damaged = (long) rounds * tATK;
                    if(damaged >= humanCurHP) {
                        result = false;
                        break;
                    }
                    else {
                        humanCurHP -= damaged;
                    }
                }else { // 포션
                    humanAtk += tATK;
                    humanCurHP += tHP;
                    if (humanCurHP > mid){
                        humanCurHP = mid;
                    }
                }

            }

            if (result){
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }
}
