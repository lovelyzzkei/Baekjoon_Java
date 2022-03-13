import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_3876 {    

    static int n;
    static String[][] rule;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            rule = new String[n][2];
            for (int i = 0; i < n; i++) {
                rule[i] = br.readLine().split(" ");
            }
    
            String gamma = br.readLine();
            String delta = br.readLine();

            HashMap<String, Boolean> visited = new HashMap<>();
            visited.put(gamma, true);

            Deque<String> q = new ArrayDeque<>();
            q.add(gamma);

            int cnt = 0;
            boolean isFind = false;

            while (!q.isEmpty()) {

                int qSize = q.size();
                for (int t = 0; t < qSize; t++) {

                    String curr = q.pop();
                    if (curr.equals(delta)) {
                        isFind = true;
                        break;
                    }

                    for (int i = 0; i < n; i++) {
                        String alpha = rule[i][0];
                        String beta = rule[i][1];

                        String next = curr.replace(alpha, beta);
                        // System.out.println(next);
                        if (next.length() <= delta.length() && visited.get(next) == null) {
                            visited.put(next, true);
                            q.add(next);
                        }
                    }
                }

                if (isFind) break;
                cnt++;
            }
            // System.out.println(cnt);
            System.out.println(isFind ? cnt : -1);
           
        }
        
        bw.flush();
        bw.close();

    }
}

