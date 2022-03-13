import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1783 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int ret;

        if (n > 2 * m) {
            ret = Math.min((n-1)/2, (m-1)/2);
        } else {
            ret = 0;
        }

        if (n == 1 && m == 1) {
            bw.write(1 + "");
        } else {
            bw.write(String.format("%d", ret*2));
        }
        bw.flush();
        bw.close();

    }
}
