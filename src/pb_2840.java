import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2840 {
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        Deque<String> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            dq.add("");
        }

        boolean isImpossible = false;
        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");

            int move = Integer.parseInt(input[0]);
            for (int j = 0; j < move; j++) {
                dq.add(dq.poll());
            }

            if ("".equals(dq.peek()) || input[1].equals(dq.peek())) {
                dq.poll();
                dq.addFirst(input[1]);
            } else {
                isImpossible = true;
                break;
            }
        }

        // 중복 알파벳이 있는지 확인
        boolean[] alphabet = new boolean[26];
        for (String s : dq) {
            if (s != "") {
                if (alphabet[s.toCharArray()[0] - 'A']) {
                    isImpossible = true;
                    break;
                }
                alphabet[s.toCharArray()[0]-'A'] = true;
            }
        }
        

        if (isImpossible) bw.write("!");
        else {
            bw.write(dq.poll());
            while (dq.size() != 0) {
                String last = dq.pollLast();
                bw.write((last != "") ? last : "?");
            }
        }
        
        bw.flush();
        bw.close();

    }
}
