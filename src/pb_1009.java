import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1009 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            int ret = 1;
            for (int i = 0; i < b; i++) {
                ret *= a;
                ret %= 10;
            }
            ret %= 10;
            bw.write(((ret == 0) ? 10 : ret) + "\n");
        }

        bw.flush();
        bw.close();

    }
}
