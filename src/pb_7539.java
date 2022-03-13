import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_7539 {

    static int n, m;
    static SortedMap<Integer, Integer> input = new TreeMap<>();
    static int[] memory, cost, cache;
    static final int INF = 1_000_000;

    // static int calMinCost(int start, int remain) {
    //     if (memory)
    // }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);

        memory = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cost = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cache = new int[n];
        Arrays.fill(cache, -1);

        for (int i = 0; i < n; i++) {
            input.put(memory[i], cost[i]);
        }

        System.arraycopy(input.keySet().toArray(), 0, memory, 0, n);
        System.arraycopy(input.values().toArray(), 0, memory, 0, n);

        System.out.println(Arrays.toString(memory));
        System.out.println(Arrays.toString(cost));

        int ret = INF;
        // for (int i = 0; i < n; i++) {
        //     ret = Math.min(ret, calMinCost(i, m));
        // }

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
