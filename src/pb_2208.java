import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2208 {

    static int n, m;
    static int[] jewels;
    static int[][] cache;

    static int seriesSum(int start, int len) {
        if (start == n + 1) return 0;
        if (len == 0) return 0;
        if (cache[start][len] != -1) return cache[start][len];
    
        return cache[start][len];

    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        jewels = new int[n+1];
        cache = new int[n+1][n+1];


        for (int i = 1; i <= n; i++) {
            jewels[i] = Integer.parseInt(br.readLine());
            Arrays.fill(cache[i], -1);
        }

 
        int ret = seriesSum(0, 0);
        System.out.println(Arrays.toString(cache));
    
        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
