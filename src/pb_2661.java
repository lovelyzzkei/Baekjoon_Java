import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2661 {    

    static int n;
    static boolean isFind = false;
    static HashMap<String, Boolean> visited;

    static boolean isGood(String curr) {
        int currLen = curr.length();
        for (int len = 1; len <= currLen/2; len++) {
            String back = curr.substring(currLen-len, currLen);
            String front = curr.substring(currLen-2*len, currLen-len);
            // System.out.println(front + " " + back);
            if (front.equals(back)) return false;
        }
        return true;
    }

    static void dfs(String curr) {
        if (isFind) return;
        if (curr.length() == n) {
            isFind = true;
            System.out.println(curr);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            String next = curr + i;
            if (isGood(next)) {
                dfs(next);
            }
        }
        
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        visited = new HashMap<>();
        dfs(1+"");
        
        bw.flush();
        bw.close();

    }
}

