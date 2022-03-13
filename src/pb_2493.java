import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2493 {
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] height = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] ret = new int[n+1];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {       
            while (!stack.isEmpty()) {
                if (height[stack.getLast()] > height[i]) break;
                else stack.removeLast();
            }

            if (stack.isEmpty()) {
                ret[i] = 0;
            } else {
                ret[i] = stack.getLast();
            }
            stack.add(i);
        }   

        for (int i = 1; i <= n; i++) {
            bw.write(ret[i] + " ");
        }
        bw.flush();
        bw.close();

    }
}
