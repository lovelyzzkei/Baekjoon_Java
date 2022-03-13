import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_12865 {

    static int n, k;
    static int[] weight, value;
    static int[][] cache;

    static int pack(int item, int weightOfObj) {
        if (item == n) return 0;
        if (cache[item][weightOfObj] != -1) return cache[item][weightOfObj];
        cache[item][weightOfObj] = pack(item + 1, weightOfObj);
        if (weightOfObj - weight[item] >= 0) {
            cache[item][weightOfObj] = Math.max(cache[item][weightOfObj], value[item] + pack(item + 1, weightOfObj - weight[item]));
        }
        return cache[item][weightOfObj];
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        cache = new int[n][k+1];  // [아이템][무게]
        weight = new int[n];
        value = new int[n];


        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            int w = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            
            weight[i] = w;
            value[i] = v;

            Arrays.fill(cache[i], -1);
        }

        int ret = pack(0, k);

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
