import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_13398 {

    static int n;
    static int[] nums;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 앞에 더미 데이터 추가
        n = Integer.parseInt(br.readLine());
        nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] maxBackSubArray = new int[n+1];
        int[] maxFrontSubArray = new int[n+1];


        // 수를 제거하는 경우의 수와 그렇지 않은 경우를 따로 계산하여 둘 중의 최댓값을 답으로 사용
        int ret = nums[n-1];
        int maxSubHeading = nums[n-1];
        maxBackSubArray[n-1] = maxSubHeading;
        for (int i = n - 2; i >= 0; i--) {
            maxSubHeading = Math.max(maxSubHeading + nums[i], nums[i]);
            if (ret < maxSubHeading) {
                ret = maxSubHeading;
            }
            maxBackSubArray[i] = maxSubHeading;
        }

        ret = nums[0];
        maxSubHeading = nums[0];
        maxFrontSubArray[0] = maxSubHeading;
        for (int i = 1; i < n; i++) {
            maxSubHeading = Math.max(maxSubHeading + nums[i], nums[i]);
            if (ret < maxSubHeading) {
                ret = maxSubHeading;
            }
            maxFrontSubArray[i] = maxSubHeading;
        }

        int cand = Math.max(maxFrontSubArray[0], maxBackSubArray[0]);
        for (int i = 1; i < n; i++) {
            cand = Math.max(maxFrontSubArray[i-1] + maxBackSubArray[i+1], cand);
        }
        ret = Math.max(ret, cand);
        // System.out.println(Arrays.toString(maxBackSubArray));
        // System.out.println(Arrays.toString(maxFrontSubArray));

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
