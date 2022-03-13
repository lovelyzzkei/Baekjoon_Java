import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2240 {

    static int t, w;
    static int[] trees;
    static int[][] cache;

    static int eatPlum(int time, int cntMove, int pos) {
        if (time == 0 || cntMove == -1) return 0;
        if (cache[time][cntMove] != -1) return cache[time][cntMove];
        cache[time][cntMove] = (pos == trees[time]) ? 1 : 0;
        cache[time][cntMove] += Math.max(eatPlum(time-1, cntMove, pos), eatPlum(time-1, cntMove-1, 3-pos));
        return cache[time][cntMove];
    }




    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        t = Integer.parseInt(input[0]);
        w = Integer.parseInt(input[1]);

        cache = new int[t+1][w+1];
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }

        trees = new int[t+1]; trees[0] = 0;
        for (int i = 1; i <= t; i++) {
            trees[i] = Integer.parseInt(br.readLine());
        }


        int ret = Math.max(eatPlum(t, w, 1), eatPlum(t, w-1, 2));


        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
