import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_10986 {

    static int n, m;
    static int[] a, pSum;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        // 각 a_i를 M으로 나눈 나머지를 a에 저장 (모듈러 연산)
        a = Stream.of(("0 "+br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i <= n; i++) {
            a[i] %= m;
        }

        // 구간합 배열도 m으로 나눈 나머지를 저장
        pSum = new int[n+2];
        for (int i = 1; i <= n; i++) {
            pSum[i+1] = (pSum[i] + a[i]) % m;
        }

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(pSum));

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n+1; j++) {
                System.out.println(i + " " + j + " " + (pSum[j]-pSum[i])%m);
                if ((pSum[j]-pSum[i])%m == 0) cnt++;
            }
        }
        bw.write(cnt + "");


        bw.flush();
        bw.close();

    }
}
