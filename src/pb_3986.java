import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_3986 {
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            String word = br.readLine();

            Deque<Character> dq = new ArrayDeque<>();
            for (int j = 0; j < word.length(); j++) {
                if (dq.peekLast() == null || dq.peekLast() != word.charAt(j)) {
                    dq.add(word.charAt(j));
                } else {
                    dq.removeLast();
                }
            }

            if (dq.size() == 0) cnt++;
        }

        bw.write(cnt + "");
        bw.flush();
        bw.close();

    }
}
