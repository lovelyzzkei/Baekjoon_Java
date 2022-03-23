import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1949 {

    static int n;
    static int[] population;
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] visited;
    static int[] cache;

    static int dp(int curr, int prevSelected) {
        visited[curr] = true;
        if (cache[curr] != -1) return cache[curr];

        int currSelected = population[curr];
        int currNotSelected = 0;

        for (int next : tree.get(curr)) {
            if (!visited[next]) {
                if (prevSelected == 1) {
                    currNotSelected += dp(next, 0);
                } else {
                    currSelected += dp(next, 1);
                }
            }
        }

        return cache[curr] = Math.max(currSelected, currNotSelected);

    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        
        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        population = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < n-1; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        visited = new boolean[n+1];

        cache = new int[n+1];
        Arrays.fill(cache, -1);
        // for (int[] arr : cache) {
        //     Arrays.fill(arr, -1);
        // }

        // 1번 마을이 우수 마을로 선정되는 경우와 그렇지 않은 경우 중의 최댓값을 반환
        int ret = Math.max(dp(1, 0), dp(1, 1));
        bw.write(ret+ "");
        bw.flush();
        bw.close();

    }
}
