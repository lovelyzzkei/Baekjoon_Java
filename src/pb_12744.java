import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_12744 {

    static int n;
    static HashMap<String, Boolean> visited;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        String size = "";
        String dir = "";

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            size += input[0];
            dir += input[1];
        }

        String start = (size + " " + dir);

        Deque<String> q = new ArrayDeque<>();
        q.add(start);
        
        visited = new HashMap<>();
        visited.put(start, true);

        int cnt = 0;
        boolean isFinished = false;


        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int t = 0; t < qSize; t++) {
                String[] curr = q.pop().split(" ");
                size = curr[0];
                dir = curr[1];

                char[] sizeArr = size.toCharArray();
                Arrays.sort(sizeArr);

                Set<Character> dirSet = new HashSet<>();
                for (char c : dir.toCharArray()) {
                    dirSet.add(c);
                }


                if (size.equals(new String(sizeArr)) && dirSet.size() == 1 && dirSet.contains('+')) {
                    isFinished = true;
                    break;
                }

                for (int i = 0; i < n; i++) {
                    String targetCake = size.substring(0, i+1);
                    StringBuffer sbCake = new StringBuffer(targetCake);
                    String nextCake = (sbCake.reverse().toString() + size.substring(i+1));

                    StringBuffer sbDir = new StringBuffer(dir);
                    for (int j = 0; j <= i; j++) {
                        sbDir.replace(j, j+1, (dir.charAt(i-j) == '+' ? "-" : "+"));
                    }
                    String nextDir = sbDir.toString();
                    String next = nextCake + " " + nextDir;

                    if (visited.get(next) == null) {
                        visited.put(next, true);
                        q.add(next);
                    }

                }
            }

            if (isFinished) break;
            cnt++;
        }

        bw.write(cnt+"");
        bw.flush();
        bw.close();

    }
}

