import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_3973 {

    static int n;
    static HashMap<Integer, HashMap<Integer, Integer>> cache;
    static final int INF = 100001;

    static int dfs(int curr, int prev) {
        if (cache.get(curr).get(prev) != null && cache.get(curr).get(prev) != 0) return cache.get(curr).get(prev);
        
        cache.get(curr).put(prev, 0);

        // for (Integer i : cache.keySet()) {
        //     System.out.print(cache.get(i).toString() + " ");
        // }
        // System.out.println();

        for (Integer next : cache.get(curr).keySet()) {
            if (next != prev && next != curr) {
                cache.get(curr).put(prev, Math.max(cache.get(curr).get(prev), 1 + dfs(next, curr)));
            }
        }
        return cache.get(curr).get(prev);
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int c = Integer.parseInt(br.readLine());

        for (int t = 0; t < c; t++) {
            n = Integer.parseInt(br.readLine());

            cache = new HashMap<>();
            for (int i = 0; i < n; i++) {
                cache.put(i, new HashMap<Integer, Integer>());
            }


            for (int i = 0; i < n-1; i++) {
                String[] input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);

                cache.get(a).put(b, 0);
                cache.get(b).put(a, 0);
            }

            

            int ret = INF;
            for (int i = 0; i < n; i++) {
                ret = Math.min(ret, dfs(i, i));
            }

            bw.write(ret + "\n");
        }
        

        bw.flush();
        bw.close();

    }
}
