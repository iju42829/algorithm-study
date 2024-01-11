/*
백준 온라인 저지 7682번 틱택토(Gold V)
 */

import java.lang.*;
import java.io.*;

public class BOJ_7682 {
    public static boolean checkBingo(String s, String[][] ttt) {
        if(ttt[0][0].equals(s) && ttt[1][1].equals(s) && ttt[2][2].equals(s)) {
            return true; // 우하향 대각선 빙고 여부 체크
        }
        if(ttt[0][2].equals(s) && ttt[1][1].equals(s) && ttt[2][0].equals(s)) {
            return true; // 우상향 대각선 빙고 여부 체크
        }

        for(int i=0; i<3; i++) {
            if(ttt[i][0].equals(s) && ttt[i][1].equals(s) && ttt[i][2].equals(s)) {
                return true; // 가로 빙고 여부 체크
            } else if(ttt[0][i].equals(s) && ttt[1][i].equals(s) && ttt[2][i].equals(s)) {
                return true; // 세로 빙고 여부 체크
            }
        }

        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[][] ttt = new String[3][3];

        while(true) {
            String cmd = br.readLine().trim();
            if(cmd.equals("end")) {
                break;
            }
            String[] cmdArr = cmd.split("");

            int cntX = 0, cntO = 0;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    ttt[i][j] = cmdArr[3*i + j];
                    if(ttt[i][j].equals("X")) {
                        cntX++;
                    } else if(ttt[i][j].equals("O")) {
                        cntO++;
                    }
                }
            }

            boolean validGame = true;
            if(cntX < cntO || cntX > cntO + 1 || cntX + cntO < 5) {
                // X의 수는 반드시 O보다 1개 많거나 같아야 함.
                // 또한 4턴 내에 게임을 끝낼 수 없음.
                validGame = false;
            } else {
                if(checkBingo("X", ttt) && checkBingo("O", ttt)) {
                    // 두 사람이 모두 빙고를 달성할 수 없음
                    validGame = false;
                } else if(!checkBingo("X", ttt) && !checkBingo("O", ttt)) {
                    // 승부가 나지 않았음에도 판을 전부 채우지 않으면 유효한 게임이 아님
                    if(cntX + cntO != 9) validGame = false;
                } if((checkBingo("X", ttt) && cntX == cntO) || (checkBingo("O", ttt) && cntX > cntO)) {
                    // 이미 승부가 났는데 판을 더 채우려 시도한 경우
                    validGame = false;
                }
            }

            if(validGame) sb.append("valid\n");
            else sb.append("invalid\n");
        }

        br.close();
        String str = sb.toString();
        System.out.println(str);
    }
}