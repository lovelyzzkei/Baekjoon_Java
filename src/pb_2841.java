import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2841 {
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int p = Integer.parseInt(input[1]);

        ArrayList<Deque<Integer>> line = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            line.add(new ArrayDeque<>(Arrays.asList(0)));
        }   

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            int lineNumber = Integer.parseInt(input[0]);
            int fret = Integer.parseInt(input[1]);
            
            Deque<Integer> dq = line.get(lineNumber);
            if (dq.getLast() > fret) {
                while (dq.getLast() > fret) {
                    dq.removeLast();
                    cnt++;
                }
            }

            if (dq.getLast() < fret) {
                dq.add(fret);
                cnt++;
            } 

            line.set(lineNumber, dq);
        }

        bw.write(cnt + "");
        bw.flush();
        bw.close();

    }
}
