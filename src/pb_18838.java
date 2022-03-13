import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_18838 {

    static int n, k;
    static int[] a, cacheLis, cacheCnt, L;

    static int binarySearch(int target, int len) {
        int mid = 0;
        int start = 1, end = len;

        while (start <= end) {
            mid = (start + end) / 2;

            if (target == L[mid]) return mid;
            if (target < L[mid]) end = mid - 1;
            else start = mid + 1;

        }

        return (L[mid] < target) ? mid + 1 : mid;
    }

    // 이분 탐색을 이용하여 각 원소에서의 lis의 길이를 구하여 cacheLis에 저장
    static int lis(){
        int len = 0;
        for (int i = 1; i <= n; i++) {
            int now = a[i];

            // L의 가장 뒤에 있는 원소가 현재 수보다 작으면 현재 수를 추가
            if (L[len] < now) {
                len++;
                L[len] = now;
            } else {
                int idx = binarySearch(now, len);
                L[idx] = now;
            }
            cacheLis[i] = len;

        }
        return len;
    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        
        // 맨 앞에 더미 데이터 추가
        a = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        cacheLis = new int[n+1];
        cacheCnt = new int[n+1];
        L = new int[n+1];


        int longestLis = lis();




        System.out.println(Arrays.toString(cacheLis));
        bw.write(longestLis + " ");
        bw.flush();
        bw.close();

    }
}
