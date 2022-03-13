import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_14002 {

    static int n;
    static int[] a, cache, choices;

    static int lis(int start) {
        if (cache[start] != -1) return cache[start];
        cache[start] = 1;
        int bestNext = -1;
        for (int i = start + 1; i < n; i++) {
            if (a[start] < a[i]) {
                int cand = lis(i) + 1;
                if (cache[start] < cand) {
                    cache[start] = cand;
                    bestNext = i;
                }
                
            }
        }
        choices[start] = bestNext;
        return cache[start];
    }

    static void reconstruct(int start) {
        if (start != -1) {
            System.out.print(a[start] + " ");
        }
        int next = choices[start];
        if (next != -1) reconstruct(next);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        a = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cache = new int[n];
        choices = new int[n+1];
        Arrays.fill(cache, -1);

        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, lis(i));
        }

        System.out.println(ret);
        int idx = 0;
        while (cache[idx] != ret) {
            idx++;
        }
        reconstruct(idx);
        System.out.println();

        bw.flush();
        bw.close();

    }
}
