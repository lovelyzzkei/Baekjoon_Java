import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class pb_9094 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int _t = 0; _t < t; _t++) {

            String input[] = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            int cnt = 0;

            for (int b = 1; b < n; b++) {
                for (int a = 1; a < b; a++) {
                    int left = (int) Math.pow(a, 2) + (int) Math.pow(b, 2) + m;
                    int right = a * b;
                    if (left % right == 0) {
                        cnt += 1;
                    }
                }
            }
            bw.write(String.valueOf(cnt) + '\n');
        }
        bw.flush();
        bw.close();
    }
}
