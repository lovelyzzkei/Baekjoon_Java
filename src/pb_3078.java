import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_3078 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        ArrayList<Deque<Integer>> lengthOfNames = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            lengthOfNames.add(new ArrayDeque<>());
        }

        long cnt = 0;
        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            int length = name.length();

            Deque<Integer> names = lengthOfNames.get(length);
            while (names.peek() != null && i - names.peek() > k) {
                names.poll();
            }
            cnt += names.size();
            lengthOfNames.get(name.length()).add(i);
        }        
        

        bw.write(cnt+"");
        bw.flush();
        bw.close();

    }
}
