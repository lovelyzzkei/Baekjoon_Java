import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_16500 {

    static int n;
    static String s;
    static String[] words;
    static boolean[] cache;

    static boolean wordCheck(int idx) {
        if (idx == s.length()) return true;
        if (cache[idx]) return true;
        for (int i = 0; i < n; i++) {
            if (words[i].indexOf(s.charAt(idx)) != -1) {
                return cache[idx] = wordCheck(idx+1);
            }
        }
        return cache[idx] = (false && wordCheck(idx+1));
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        s = br.readLine();
        n = Integer.parseInt(br.readLine());
        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        cache = new boolean[s.length()];
        Arrays.fill(cache, false);

        int ret = wordCheck(0) ? 1 : 0;

        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
