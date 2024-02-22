package chan.GOLD_IV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/16398

public class BOJ_16368 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] space = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Edge[] edges = new Edge[(N * (N - 1)) / 2];
        int edgeIndex = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                edges[edgeIndex++] = new Edge(i, j, space[i][j]);
            }
        }

        KruskalMST kruskalMST = new KruskalMST(N, edges);
        kruskalMST.kruskalMST();
    }

    static class Edge {
        int start, end, weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static class KruskalMST {
        private int V, E;
        private Edge[] edges;

        public KruskalMST(int v, Edge[] edges) {
            this.V = v;
            this.E = edges.length;
            this.edges = edges;
        }

        private int find(int[] parent, int i) {
            if (parent[i] == -1) return i;
            return find(parent, parent[i]);
        }

        private void union(int[] parent, int x, int y) {
            int xSet = find(parent, x);
            int ySet = find(parent, y);
            parent[xSet] = ySet;
        }

        public void kruskalMST() {
            Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));

            int[] parent = new int[V];
            Arrays.fill(parent, -1);

            long totalWeight = 0;

            for (int i = 0; i < E; ++i) {
                Edge nextEdge = edges[i];

                int x = find(parent, nextEdge.start);
                int y = find(parent, nextEdge.end);

                if (x != y) {
                    totalWeight += nextEdge.weight;
                    union(parent, x, y);
                }
            }
            System.out.println(totalWeight);
        }
    }
}


