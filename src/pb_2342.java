import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2342 {

    static int[] instruction;
    static int[][][] cache;
    static final int INF = 400001;

    static int playDDR(int next, int left, int right){
        int nextStep = instruction[next];
        if (nextStep == 0) return 0;
        if (left == right && left != 0) return 0;
        if (cache[next][left][right] != INF) return cache[next][left][right];

        if (nextStep == (left%2+1) || nextStep == (left%2+3)) {
            cache[next][left][right] = Math.min(cache[next][left][right], 3 + playDDR(next+1, nextStep, right));
        }
        if (nextStep == (right%2+1) || nextStep == (right%2+3)) {
            cache[next][left][right] = Math.min(cache[next][left][right], 3 + playDDR(next+1, left, nextStep));
        }

        if (left == 0) {
            cache[next][left][right] = Math.min(cache[next][left][right], 2 + playDDR(next+1, nextStep, right));
        }
        if (right == 0) {
            cache[next][left][right] = Math.min(cache[next][left][right], 2 + playDDR(next+1, left, nextStep));
        }
        if (left == nextStep || right == nextStep) {
            cache[next][left][right] = Math.min(cache[next][left][right], 1 + playDDR(next+1, left, right));
        }


        return cache[next][left][right];
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        instruction = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        cache = new int[instruction.length][5][5];
        for (int[][] arr : cache) {
            for (int[] tmp : arr) {
                Arrays.fill(tmp, INF);
            }
        }


        int ret = playDDR(0, 0, 0);

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
