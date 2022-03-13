import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_16397 {    

    static final int INF = 99999;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int t = Integer.parseInt(input[1]);
        int g = Integer.parseInt(input[2]);

        Deque<Integer> q = new ArrayDeque<>();
        q.add(n);

        boolean[] visited = new boolean[INF+2];
        visited[n] = true;

        int cnt = 0;
        boolean isFound = false;

        while (!q.isEmpty() && cnt <= t) {

            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int curr = q.pop();
                if (curr == g) {
                    isFound = true;
                    break;
                }
    
                int resultA = curr + 1;
                if (resultA <= INF && !visited[resultA]) {
                    visited[resultA] = true;
                    q.add(resultA);
                }
    
                int doubleCurr = curr * 2;
                int length = (int) Math.log10(doubleCurr);
                int resultB = doubleCurr - (int) Math.pow(10, length);
                if (doubleCurr <= INF && resultB <= INF && !visited[resultB]) {
                    visited[resultB] = true;
                    q.add(resultB);
                }
            }

            if (isFound) break;
            cnt++;
        }

        if (isFound) bw.write(cnt + "");
        else bw.write("ANG");

        bw.flush();
        bw.close();

    }
}

