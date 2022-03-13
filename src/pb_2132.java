import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2132 {

    static int n;
    static int[] fruit;
    static HashMap<Integer, HashMap<Integer, Integer>> cache;

    static int dfs(int curr, int prev) {
        if (cache.get(curr).get(prev) != null && cache.get(curr).get(prev) != 0) return cache.get(curr).get(prev);

        cache.get(curr).put(prev, fruit[curr]);
        for (Integer next : cache.get(curr).keySet()) {
            if (next != 0 && next != curr && next != prev) {
                cache.get(curr).put(prev, Math.max(cache.get(curr).get(prev), fruit[curr] + dfs(next, curr)));
            }
        }

        return cache.get(curr).get(prev);
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        fruit = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();


        // DP 수행할 cache 배열 초기화
        cache = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            cache.put(i, new HashMap<Integer, Integer>());
        }

        for (int i = 0; i < n-1; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            cache.get(u).put(v, 0);
            cache.get(v).put(u, 0);
        }


        int ret = 0;
        int idx = 1;
        for (int i = 1; i <= n; i++) {
            int numOfFruit = dfs(i, 0);

            if (numOfFruit > ret) {
                ret = numOfFruit;
                idx = i;
            }
        }

        
        bw.write(ret + " " + idx);
        bw.flush();
        bw.close();

    }
}
