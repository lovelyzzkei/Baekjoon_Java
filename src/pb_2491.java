import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2491 {

    static int n;
    static int[] nums, cacheInc, cacheDec;
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        cacheInc = new int[n]; cacheInc[0] = 1;
        cacheDec = new int[n]; cacheDec[0] = 1;

        int ret = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i-1] < nums[i]) {
                cacheInc[i] = cacheInc[i-1] + 1;
                cacheDec[i] = 1;
            } 
            else if (nums[i-1] > nums[i]) {
                cacheInc[i] = 1;
                cacheDec[i] = cacheDec[i-1] + 1;
            } else {
                cacheInc[i] = cacheInc[i-1] + 1;
                cacheDec[i] = cacheDec[i-1] + 1;
            }

            ret = Math.max(ret, Math.max(cacheInc[i], cacheDec[i]));

        }

        // System.out.println(Arrays.toString(cacheInc));```
        // System.out.println(Arrays.toString(cacheDec));

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
