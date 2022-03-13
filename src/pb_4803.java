import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_4803 {  

    static int n;
    static boolean[] cycle, visited, finished;
    static ArrayList<ArrayList<Integer>> graph;
    

    static boolean dfs(int curr, int prev) {
        visited[curr] = true;
        for (Integer next : graph.get(curr)) {
            if (next != prev) {
                if (!visited[next]) {
                    cycle[curr] = dfs(next, curr);
                }
            
                else if (visited[next] && !finished[next]) {
                    cycle[curr] = true;
                } 
            } else {
                cycle[curr] = cycle[curr] || cycle[prev];
            }
            
        }
        finished[curr] = true;
        return cycle[curr];
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numOfCase = 1;

        while (true) {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            // 입력이 둘다 0이면 종료
            if (n == 0 && m == 0) break;

            // 연결 요소의 개수를 세는데 이게 트리인지 그래프인지(사이클) 판별하여
            // 트리의 개수만 세도록 구현

            // 간선의 정보를 담을 arraylist 선언 및 초기화
            graph = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<Integer>());
            }
            
            // 간선을 입력받아 arraylist에 저장
            for (int i = 0; i < m; i++) {
                input = br.readLine().split(" ");
                int u = Integer.parseInt(input[0]);
                int v = Integer.parseInt(input[1]);

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            // 방문 정보를 저장할 visited 배열을 선언 및 초기화 하고
            // 각 정점에 대해 dfs를 돌리며 트리를 개수를 구함
            visited = new boolean[n+1];
            finished = new boolean[n+1];
            cycle = new boolean[n+1];


            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    cycle[i] = dfs(i, 0);
                    if (!cycle[i]) cnt++;
                }
            }
            
            switch (cnt) {
                case 0:
                    bw.write(String.format("Case %d: No trees.\n", numOfCase++));
                    break;
                case 1:
                    bw.write(String.format("Case %d: There is one tree.\n", numOfCase++));
                    break;
                default:
                    bw.write(String.format("Case %d: A forest of %d trees.\n", numOfCase++, cnt));
                    break;
            }
        }
        
        bw.flush();
        bw.close();

    }
}
