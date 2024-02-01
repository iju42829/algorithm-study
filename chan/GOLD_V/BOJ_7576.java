package chan.GOLD_V;

//https://www.acmicpc.net/problem/7576
/*
    - 토마토
        - 익은 토마토 주변의 토마토들도 익게 됨 (대각선 x)
        - 최소일수를 구하기
        - 입력
            - 상자의 크기 M*N
            - 익은토마토 -> 1
            - 익지 않은 토마토 -> 0
            - 토마토가 없음 -> -1
            - 토마토가 하나 이상이어야 함
        - 출력
            - 모두 익을 최소 날짜
                - 처음부터 모두 익어있으면 0출력
                - 모두 익지 못하는 상황이면 -1
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_7576 {
    public static void main (String[] args) throws IOException{
        tomato();
    }
    private static void tomato() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int [][] arr = new int [n][m];

        for (int i = 0; i<n; i++){
            StringTokenizer rt = new StringTokenizer(br.readLine());
            for (int j =0; j < m; j++) {
                arr[i][j] = Integer.parseInt(rt.nextToken());
            }
        }
        int days = getDays(arr,m ,n);
        System.out.println(days);
    }

    private static int getDays(int [][] arr,int m, int n) {
        int days = 0;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0 ,-1, 0};
        boolean [][] visited = new boolean[n][m];

        Queue<Tomato> queue = new LinkedList<>();

        for (int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 1){ // 익은 토마토 큐에 추가
                    queue.offer(new Tomato(i,j,0));
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            Tomato now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = now.x + dx[i]; // 상하좌우 탐색
                int y = now.y + dy[i];

                if (x >= 0 && y >= 0 && x < n && y < m) { // 배열 범위에 있는지
                    if (arr[x][y] == 0 && !visited[x][y]) { // 안 익은 토마토 면
                        visited[x][y] = true;
                        arr[x][y] = 1;

                        queue.offer(new Tomato(x, y, now.days + 1));
                        days = now.days + 1;
                    }
                }
            }
        }
        for(int i = 0; i< n; i++){
            for (int j = 0; j < m; j++){
                if (arr[i][j]==0){ // 익지 못하는 토마토가 있는 경우
                    return -1;
                }
            }
        }
        return days;
    }
    static class Tomato {
        int x, y ,days;
        Tomato(int x, int y , int days){
            this.x= x;
            this.y = y;
            this.days = days;
        }
    }
}
