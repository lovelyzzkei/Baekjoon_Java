import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class test {    

    static int n;
    static int[] birthNum = {8, 5, 3};
    static final int INF = 1_000_001;

    static int calcSumDigit(String num) {
        int sum = 0;
        for (int i = 0; i < num.length(); i++) {
            sum += (num.charAt(i) - '0');
        }
        return sum;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());

            Deque<String> q = new ArrayDeque<>();
            q.add("3"); q.add("5"); q.add("8");


            HashMap<String, Boolean> visited = new HashMap<>();
            visited.put("3", true);
            visited.put("5", true);
            visited.put("8", true);

            String ret = "";
            boolean isArrived = false;
            while (!q.isEmpty()) {

                int qSize = q.size();
                for (int k = 0; k < qSize; k++) {
                    String curr = q.pop();
                    int sumOfDigit = calcSumDigit(curr);

                    if (sumOfDigit == n) {
                        isArrived = true;
                        ret = curr;
                        break;
                    }

                    for (int b : birthNum) {
                        String next = curr + b;

                        if (sumOfDigit+b > n || visited.get(next) != null) continue;
                        visited.put(next, true);
                        q.add(next);
                    }
                }

                if (isArrived) break;
            }

            bw.write((isArrived ? ret : -1) + "\n");
        }


        bw.flush();
        bw.close();

    }
}

