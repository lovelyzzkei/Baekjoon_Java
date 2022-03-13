import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class pb_6131 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int cnt = 0;

        for (int a = 1; a <= 500; a++) {
            for (int b = 1; b <= a; b++) {
                int squareA = (int) Math.pow(a, 2);
                int squareB = (int) Math.pow(b, 2) + n;
                // System.out.println(String.format("%d %d", squareA, squareB));
                if (squareA == squareB) {
                    cnt += 1;
                }
            }
        }
        bw.write(String.format("%d", cnt));
        bw.flush();
        bw.close();
    }
}
