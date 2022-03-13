import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2629 {

    static int n;
    static int[] weight, reverseWeight;
    static int[][] cache;

    static int scaleMarble(int item, int needWeight) {
        if (needWeight == 15000) return 1;
        if (item == n || needWeight > 40000) return 0;
        if (cache[item][needWeight] != -1) return cache[item][needWeight];
        cache[item][needWeight] = scaleMarble(item + 1, needWeight);
        int cand = Math.max(scaleMarble(item + 1, needWeight - weight[item]), scaleMarble(item + 1, needWeight + weight[item]));
        return cache[item][needWeight] = Math.max(cache[item][needWeight], cand);
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        weight = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        reverseWeight = new int[n];
        for (int i = 0, j = n-1; i < n; i++, j--) {
            reverseWeight[i] = weight[j];
        }
        cache = new int[n+1][40001];

        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }
        

        int numOfMarble = Integer.parseInt(br.readLine());
        int[] weightOfMarble = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int w : weightOfMarble) {
            if (w > 15000) {
                bw.write("N ");
            } else {
                bw.write((scaleMarble(0, 15000 + w) == 1) ? "Y " : "N ");
            }
        }

        

        bw.flush();
        bw.close();

    }
}
