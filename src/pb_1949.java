import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1949 {

    static int n;
    static int[] population;
    static int[][] cache;
    static ArrayList<ArrayList<Integer>> graph, tree;
    static boolean[] visited;

    // 트리를 만드는 재귀 함수
    static void dfs(int curr) {
        visited[curr] = true;
        for (int next : graph.get(curr)) {
            if (!visited[next]) {
                tree.get(curr).add(next);
                dfs(next);
            }
        }
    }

    
    static int goodTown(int curr, int isGood) {
        if (cache[curr][isGood] != -1) return cache[curr][isGood];

        // 현재 마을을 우수 마을로 선정할 경우
        if (isGood == 1) {
            cache[curr][isGood] = population[curr];
            for (int next : tree.get(curr)) {
                cache[curr][isGood] += goodTown(next, 0);
            }
        }

        // 현재 마을을 우수 마을로 선정하지 않을 경우
        // 현재 마을과 인접한 마을중 적어도 하나는 우수 마을 이어야함
        else {
            cache[curr][isGood] = 0;
            for (int next : tree.get(curr)) {
                int nextGoodTown = goodTown(next, 1);
                
                cache[curr][isGood] = nextGoodTown;
            }
        }
        return cache[curr][isGood];
    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        
        graph = new ArrayList<>();
        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            tree.add(new ArrayList<>());
        }

        population = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < n-1; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 먼저 주어진 그래프를 트리로 만듬
        visited = new boolean[n+1];
        dfs(1); 

        cache = new int[n+1][2];      
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }

        // 1번 마을이 우수 마을로 선정되는 경우와 그렇지 않은 경우 중의 최댓값을 반환
        int ret = Math.max(goodTown(1, 1), goodTown(1, 0));

        for (int[] arr : cache) {
            System.out.println(Arrays.toString(arr));
        }
        bw.write(ret+ "");
        bw.flush();
        bw.close();

    }
}
