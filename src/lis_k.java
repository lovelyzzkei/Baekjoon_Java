import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class lis_k {

    static int n;
    static int[] nums, idxOfNums, L;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        

    static int binarySearch(int target, int len) {
        int mid = 0;
        int start = 1, end = len;

        while (start <= end) {
            mid = (start + end) / 2;

            if (target == L[mid]) return mid;
            if (target < L[mid]) end = mid - 1;
            else start = mid + 1;
        }
        if (L[mid] < target) return mid + 1;
        else return mid;
    }

    static void backtrace(int idx, int num) throws IOException {
        if (idx == 0) return;
        if (idxOfNums[idx] == num) {
            backtrace(idx - 1, num - 1);
            bw.write(String.format("%d ", nums[idx]));
        } else {
            backtrace(idx - 1, num);
        }
    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        nums = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        idxOfNums = new int[n+1];
        L = new int[n+1];
        L[0] = -1_000_000_001;

        int len = 0;
        for (int i = 1; i <= n; i++) {
            int now = nums[i];

            // L의 마지막 원소보다 현재의 값이 더 클 경우 L 뒤에 값을 추가.
            if (now > L[len]) {
                len++;
                L[len] = now;
                idxOfNums[i] = len;
            } else {
                // 그렇지 않을 경우 switch
                int idx = binarySearch(now, len);
                L[idx] = now;
                idxOfNums[i] = idx;
            }
            // System.out.println(Arrays.toString(L));
            // System.out.println(Arrays.toString(idxOfNums));

        }
        System.out.println(len);
        backtrace(n, len);

        bw.flush();
        bw.close();

    }
}
