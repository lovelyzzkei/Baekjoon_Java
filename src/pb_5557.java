import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_5557 {

    static int n;
    static int[] nums;
    static long[][] cache;

    // idx 부터 n-2 번째 까지의 수로 n을 만들 수 있는 경우의 수
    static long makeEq(int idx, int sum) {
        if (idx == n - 1) {
            return (sum == nums[n-1]) ? 1 : 0;
        }
        if (cache[idx][sum] != -1) return cache[idx][sum];
        cache[idx][sum] = 0;
        if (sum + nums[idx] <= 20) {
            cache[idx][sum] += makeEq(idx + 1, sum + nums[idx]);
        }
        if (sum - nums[idx] >= 0) {
            cache[idx][sum] += makeEq(idx + 1, sum - nums[idx]);
        }

        // for (long[] arr : cache) {
        //     System.out.println(Arrays.toString(arr));
        // }
        // System.out.println();

        return cache[idx][sum];
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cache = new long[n][21];
        for (long[] arr : cache) {
            Arrays.fill(arr, -1);
        }

        long ret = makeEq(1, nums[0]);
        

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
