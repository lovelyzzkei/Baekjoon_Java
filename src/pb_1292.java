import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1292 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] pSum = new int[1001];
        int cnt = 0;
        int num = 1;
        for (int i = 1; i <= 1000; i++) {
            pSum[i] = pSum[i-1] + num;
            cnt++;
            if (cnt == num) {
                num++;
                cnt = 0;
            }
        }

        String[] input = br.readLine().split(" ");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);

        bw.write((pSum[b] - pSum[a-1]) + "");


        bw.flush();
        bw.close();

    }
}
