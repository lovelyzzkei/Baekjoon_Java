import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_10835 {

    static int n;
    static int[] leftCard, rightCard;
    static int[][] cache;

    static int max(int a, int b) {
        return Math.max(a, b);
    }

    static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
     
    static int cardGame(int left, int right) {
        if (left == n || right == n) return 0;
        if (cache[left][right] != -1) return cache[left][right];

        cache[left][right] = 0;
        if (leftCard[left] > rightCard[right]) {
            cache[left][right] = max(cardGame(left+1, right+1), cardGame(left+1, right), cardGame(left, right+1) + rightCard[right]);
        } else {
            cache[left][right] = max(cardGame(left+1, right+1), cardGame(left+1, right));
        }
        return cache[left][right];
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        leftCard = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rightCard = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        cache = new int[n][n];
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }

        int ret = cardGame(0, 0);

        bw.write(ret + "");
        bw.flush();
        bw.close();

    

    }
}
