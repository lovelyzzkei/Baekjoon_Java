import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1102 {
    
    static int n, p;
    static int[][] cost, cache;
    static final int INF = 600;

    static int minimizeCost(int here, int visited) {
        if (visited >= (1<<p)-1) return 0;
        if (cache[here][visited] != -1) return cache[here][visited];
        cache[here][visited] = INF;
        for (int next = 0; next < n; next++) {
            if ((visited & (1 << next)) >= 1) continue;
            cache[here][visited] = Math.min(cache[here][visited], cost[here][next] + minimizeCost(next, visited + (1 << here)));
        }
        return cache[here][visited];
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        
        // 비용 입력 받기
        cost = new int[n][n];
        for (int[] arr : cost) {
            int[] tmp = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(tmp, 0, arr, 0, n);
        }

        // 메모이제이션 할 배열 초기화
        cache = new int[n][1<<n];
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }
        
        // 나머지 입력 받음
        String statusString = br.readLine();
        p = Integer.parseInt(br.readLine());

        int statusInt = 0;
        for (int i = 0; i < n; i++) {
            statusInt += (statusString.charAt(i) == 'Y') ? (1 << i) : 0;
        }

        System.out.println(statusInt);


        // 현재 켜져 있는 발전소를 순회하며 비용의 최솟값을 구함
        int ret = INF;
        for (int i = 0; i < n; i++) {
            if ((statusInt & (1 << i)) >= 1) {
                ret = Math.min(ret, minimizeCost(i, statusInt));
            }
        }

        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
