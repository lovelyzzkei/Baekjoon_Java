import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_17203 {

    static int n, q;
    static int[] speed, variance, pSum;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        q = Integer.parseInt(input[1]);

        speed = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        
        // 전처리. 먼저 i초와 i+1초 사이의 변화량의 값을 저장
        variance = new int[n+1];
        for (int i = 1; i < n; i++) {
            variance[i] = (int) Math.abs(speed[i+1] - speed[i]);
        }

        // System.out.println(Arrays.toString(speed));
        // System.out.println(Arrays.toString(variance));

        // 변화량의 구간합 배열
        pSum = new int[n+1];
        for (int i = 0; i < n; i++) {
            pSum[i+1] = pSum[i] + variance[i];
        }

        for (int t = 0; t < q; t++) {
            input = br.readLine().split(" ");
            int i = Integer.parseInt(input[0]);
            int j = Integer.parseInt(input[1]);

            bw.write(pSum[j]-pSum[i] + "\n");
        }




        bw.flush();
        bw.close();

    }
}
