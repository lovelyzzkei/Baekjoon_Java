import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1267 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] call = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] calcA = new int[10001]; 
        int[] calcB = new int[10001]; 

        int cnt = 1;
        for (int i = 1; i <= 10000; i++) {
            if (i % 30 == 0) cnt++;
            calcA[i] = cnt*10;
        }

        cnt = 1;
        for (int i = 1; i <= 10000; i++) {
            if (i % 60 == 0) cnt++;
            calcB[i] = cnt*15;
        }

        int a = 0;
        int b = 0;

        for (int i = 0; i < n; i++) {
            a += calcA[call[i]];
            b += calcB[call[i]];
        }

        if (a > b) {
            bw.write("M " + b);
        } else if (a < b) {
            bw.write("Y " + a);
        } else {
            bw.write("Y M "+ a);
        }


        bw.flush();
        bw.close();

    }
}
