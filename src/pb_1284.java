import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1284 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            char[] num = br.readLine().toCharArray();
            if (num[0] == '0') break;

            int ans = 1;
            for (char c : num) {
                int cToInt = c - '0';
                ans++;
                if (cToInt == 0) ans += 4;
                else if (cToInt == 1) ans += 2;
                else ans += 3;
            }
            bw.write(ans + "\n");
        }
   
        bw.flush();
        bw.close();

    }
}
