import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1343 {

    static final int INF = 1_000_000;

    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String tmp = br.readLine();
        StringTokenizer st = new StringTokenizer(tmp, "[.+]", true);
        String[] input = new String[st.countTokens()];

        int idx = 0;
        while (st.hasMoreTokens()) {
            input[idx++] = st.nextToken();
        }
        String[] ans = new String[input.length];


        for (int j = 0; j < input.length; j++) {
            String s = input[j];
            if (!s.equals(".")) {
                String ret = "";
                int numOfA = s.length() / 4;
                int numOfB = (s.length() % 4) / 2;
                for (int i = 0; i < numOfA; i++) {
                    ret += "AAAA";
                }
                for (int i = 0; i < numOfB; i++) {
                    ret += "BB";
                }
                if (ret.length() == s.length()) {
                    ans[j] = ret;
                } else {
                    System.out.println(-1);
                    return;
                }
            } else {
                ans[j] = s;
            }
        }
        
        String ret = "";
        for (String s : ans) {
            ret += s;
        }

        bw.write(ret);
        bw.flush();
        bw.close();

    }
}
