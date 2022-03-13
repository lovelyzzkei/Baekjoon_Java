import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2248 {

    static int n, l;
    static long i;
    static int bino[][];

    static final int INF = Integer.MAX_VALUE;

    static void calcBino() {
        for (int i = 0; i <= 31; i++) {
            bino[i][0] = bino[i][i] = 1;
            for (int j = 1; j < i; j++) {
                bino[i][j] = Math.min(INF, bino[i-1][j-1] + bino[i-1][j]);
            }
        }
    }
    
    static String ith(int numOfOne, int len, long target) {
        if (numOfOne == 0) {
            char[] ret = new char[len-numOfOne];
            Arrays.fill(ret, '0');
            return String.valueOf(ret);
        }
        if (len == 0) {
            return "";
        }

        long skip = 0;
        for (int k = 0; k <= numOfOne; k++) {
            skip += bino[len-1][k];
        }

        if (target <= skip) {
            return "0" + ith(numOfOne, len-1, target);
        }
        return "1" + ith(numOfOne-1, len-1, target-skip);
    }

    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 입력 받기
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        l = Integer.parseInt(input[1]);
        i = Long.parseLong(input[2]);


        // DP로 이항계수 먼저 계산해놓기
        bino = new int[32][32];
        calcBino();

        
        // i번째 이진수 찾기

        bw.write(ith(l, n, i));

        bw.flush();
        bw.close();

    }
}
