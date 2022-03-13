import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1256 {

    static int n, m, k;
    static int[][] bino = new int[201][201];

    static void calcBino() {
        for (int i = 0; i <= 200; i++) {
            bino[i][0] = bino[i][i] = 1;
            for (int j = 1; j < i; j++) {
                bino[i][j] = Math.min(1_000_000_100, bino[i-1][j-1] + bino[i-1][j]);
            } 
        }
    }

    static String kth(int n, int m, int skip) {
        // a가 0개이면 나머지는 모두 z
        if (n == 0) {
            char[] ret = new char[m];
            Arrays.fill(ret, 'z');
            return new String(ret);
        }
        if (skip <= bino[n+m-1][n-1]) {
            return "a" + kth(n-1, m, skip);
        }
        return "z" + kth(n, m-1, skip - bino[n+m-1][n-1]);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        calcBino();
        if (bino[n+m][n] < k) {
            bw.write(-1 + "");
        } else {
            bw.write(kth(n, m, k));
        }

        bw.flush();
        bw.close();

    }
}
