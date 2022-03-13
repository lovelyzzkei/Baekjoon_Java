import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1525 {    

    static int[] dx = {-3, -1, 1, 3};
    static final String dest = "123456780";

    static boolean inRange(int x, int dir) {
        if (Math.abs(dir) == 3) {
            return (0 <= x+dir && x+dir < 9);
        } else {
            return (0 <= x%3+dir && x%3+dir < 3);
        }

    }

    static String swap(String curr, int start, int end) {
        char[] currArr = curr.toCharArray();
        char tmp = currArr[start];
        currArr[start] = currArr[end];
        currArr[end] = tmp;
        return String.valueOf(currArr);
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = "";
        for (int i = 0; i < 3; i++) {
            input += br.readLine().replace(" ", "");
        }

        HashMap<String, Boolean> visited = new HashMap<>();
        visited.put(input, true);

        Deque<String> q = new ArrayDeque<>();
        q.add(input);


        int move = 0;
        boolean isSuccess = false;

        while (!q.isEmpty()) {

            int qSize = q.size();
            for (int k = 0; k < qSize; k++) {

                String curr = q.pop();

                if (curr.equals(dest)) {
                    isSuccess = true;
                    break;
                }

                int idxOfBlank = curr.indexOf('0');
                for (int i = 0; i < 4; i++) {
                    int target = idxOfBlank + dx[i];
                    if (inRange(idxOfBlank, dx[i])) {
                        String next = swap(curr, idxOfBlank, target);
                        if (visited.get(next) == null) {
                            visited.put(next, true);
                            q.add(next);
                        }
                    }
                }
            }

            if (isSuccess) break;
            move++;
        }

        if (isSuccess) bw.write(move + "");
        else bw.write(-1 + "");
        bw.flush();
        bw.close();

    }
}

