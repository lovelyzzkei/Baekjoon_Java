import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_11867 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        boolean[][] visited = new boolean[101][101];

        ArrayDeque<Integer[]> dq = new ArrayDeque<>();
        dq.add(new Integer[]{n, m, 0});    
        visited[n][m] = true;

        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                Integer[] curr = dq.removeFirst();
                int first = curr[0];
                int second = curr[1];
                int person = curr[2];
                
                // System.out.println(first + " " + second);
                if (first == 1 && second == 1) {
                    bw.write((2-person)/2 + 'A');
                    bw.flush();
                    bw.close();
                }
                
                if (first != 1) {
                    for (int j = 1; j <= first; j++) {
                        if (!visited[j][first-j]) {
                            visited[j][first-j] = true;
                            dq.add(new Integer[]{j, first-j, (2-person)/2});
                        }
                    }
                }
                
                if (second != 1) {
                    for (int j = 1; j <= second; j++) {
                        if (!visited[j][second-j]) {
                            visited[j][second-j] = true;
                            dq.add(new Integer[]{j, second-j, (2-person)/2});
                        }
                    }
                }       
            }
        }

        return;
    }
}
