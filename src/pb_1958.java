import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1958 {

    static int[][][] cache;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String a = " " + br.readLine();
        String b = " " + br.readLine();
        String c = " " + br.readLine();
        
        int lenA = a.length();
        int lenB = b.length();
        int lenC = c.length();

        cache = new int[lenA][lenB][lenC];

        for (int i = 1; i < lenA; i++) {
            for (int j = 1; j < lenB; j++) {
                for (int k = 1; k < lenC; k++) {
                    if (a.charAt(i) == b.charAt(j) && b.charAt(j) == c.charAt(k)) {
                        cache[i][j][k] = cache[i-1][j-1][k-1] + 1;
                    } else {
                        cache[i][j][k] = Math.max(cache[i-1][j][k], Math.max(cache[i][j-1][k], cache[i][j][k-1]));
                    }
                }
            }
        }

        // for (int[][] arr : cache) {
        //     for (int[] tmp : arr) {
        //         System.out.print(Arrays.toString(tmp) + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();

        bw.write(String.format("%d", cache[lenA-1][lenB-1][lenC-1]));
        bw.flush();
        bw.close();

    }
}
