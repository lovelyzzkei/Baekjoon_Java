import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_1935 {
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String prefix = br.readLine();

        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            value[i] = Integer.parseInt(br.readLine());
        }

        Deque<Double> dq = new ArrayDeque<>();
        int idx = 0;
        while (idx < prefix.length()) {
            if ('A' <= prefix.charAt(idx) && prefix.charAt(idx) <= 'Z') {
                dq.add((double) value[prefix.charAt(idx) - 'A']);
            } else {
                double first = dq.removeLast();
                double second = dq.removeLast();
                switch (prefix.charAt(idx)) {
                    case '+':
                        dq.add(first + second);
                        break;
                    case '-':
                        dq.add(second - first);
                        break;
                    case '*':
                        dq.add(first * second);
                        break;
                    case '/':
                        dq.add((double) second / first);
                        break;
                }
            }
            idx++;
        }
        

        bw.write(String.format("%.2f", dq.peek()));
        bw.flush();
        bw.close();

    }
}
