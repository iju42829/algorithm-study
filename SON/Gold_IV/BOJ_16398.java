import java.util.*;
import java.io.*;

public class BOJ_16398 {
    static int n; // 행성의 개수
    static long cost = 0; // 플로우 관리비 합계
    static int[] parent; // 부모 노드 저장
    static List<int []> matrix = new ArrayList<>(); // 간선 및 관리비 저장
    public static void main(String[] args) throws IOException {
        // 초기 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parent = new int[n];

        // 노드 초기화
        for (int i = 0; i < n; i++) {
            parent[i] = i; // 자기 자신으로 초기화
        }

        for(int i=0; i<n; i++) {
            String[] info = br.readLine().split(" ");
            for(int j=i+1; j<n; j++) {
                matrix.add(new int[]{i, j, Integer.parseInt(info[j])});
            }
        }
        kruskal();

        br.close();
        System.out.println(cost);
    }

    static void kruskal() {
        // 간선 관리비용 오름차순 정렬
        matrix.sort((a, b) ->
                a[2] != b[2] ? Integer.compare(a[2], b[2]) :
                        a[0] != b[0] ? Integer.compare(a[0], b[0]) :
                                Integer.compare(a[1], b[1]));

        // 크루스칼 알고리즘
        int cnt = 0;
        for(int[] flow : matrix) {
            boolean b = isUnion(flow[0], flow[1]);
            // 두 정점의 부모 노드가 같으면 사이클
            if (!isUnion(flow[0], flow[1])) {
                cost += flow[2];
                cnt++;
            }

            if(cnt == n-1) break;
        }
    }

    // 부모 노드 탐색 메서드
    static int find(int a) {
        // 루트 노드
        if (parent[a] == a) {
            return a;
        }

        // 그 외
        return parent[a] = find(parent[a]); // 경로 압축
    }

    // 부모 노드 지정 메서드
    static boolean isUnion(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return true;
        parent[y] = x;
        return false;
    }
}