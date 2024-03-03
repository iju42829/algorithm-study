/*
백준 온라인 저지 4485번 녹색 옷 입은 애가 젤다지?(Gold IV)
 */

import java.util.*;
import java.io.*;

public class BOJ_4485 {
    static class Cave implements Comparable<Cave> {
        int y;
        int x;
        int cost;

        public Cave(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cave o) {
            return this.cost - o.cost;
        }
    }

    static int n;
    static int[][] map;
    static int[][] dir = {
            {-1, 0}, {0, -1}, {0, 1}, {1, 0}
    };

    public static void main(String[] args) throws IOException {
        // 초기 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = null;
        int a = 1;

        while(!(str = br.readLine()).equals("0")) {
            n = Integer.parseInt(str);
            map = new int[n][n];
            for(int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cost = dijkstra();
            sb.append("Problem ").append(a).append(": ").append(cost).append("\n");
            a++;
        }

        br.close();
        System.out.println(sb.toString());
    }

    static int dijkstra() {
        PriorityQueue<Cave> q = new PriorityQueue<>();
        int[][] dp = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        q.add(new Cave(0 ,0, map[0][0]));
        dp[0][0] = map[0][0];

        while(!q.isEmpty()) {
            Cave cave = q.poll();

            for(int i=0; i<4; i++) {
                int y = cave.y + dir[i][0];
                int x = cave.x + dir[i][1];
                if(y<0 || x<0 || y>=n || x>=n) {
                    continue;
                }

                if(y == n-1 && x == n-1) {
                    return cave.cost + map[y][x];
                }

                if(cave.cost + map[y][x] < dp[y][x]) {
                    dp[y][x] = cave.cost + map[y][x];
                    q.add(new Cave(y, x, dp[y][x]));
                }
            }
        }

        return dp[n-1][n-1];
    }
}