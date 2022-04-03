import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1475 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] inputArr = br.readLine().toCharArray();
        
        int[] num = new int[10];
        for (char c : inputArr) {
            num[c-'0'] += 1;
        }

        num[6] += num[9];
        num[6] = (num[6] + 1) / 2;
        num[9] = 0;

        int ret = 0;
        for (int i : num) {
            ret = Math.max(ret, i);
        }
        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
