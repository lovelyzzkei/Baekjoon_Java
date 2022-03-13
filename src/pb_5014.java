import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_5014 {

    


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String[] input = br.readLine().split(" ");
        int f = Integer.parseInt(input[0]);
        int s = Integer.parseInt(input[1]);
        int g = Integer.parseInt(input[2]);
        int u = Integer.parseInt(input[3]);
        int d = Integer.parseInt(input[4]);

        Deque<Integer> q = new ArrayDeque<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        q.add(s);
        visited.put(s, true);

        int cnt = 0;
        boolean isArrived = false;

        while (!q.isEmpty()) {
            int qSize = q.size();

            for (int i = 0; i < qSize; i++) {
                int curr = q.pop();
                
                if (curr == g) {
                    isArrived = true;
                    break;
                }

                int up = curr + u;
                int down = curr - d;
                if (up <= f && visited.get(up) == null) {
                    visited.put(up, true);
                    q.add(up);
                }
                if (down > 0 && visited.get(down) == null){
                    visited.put(down, true);
                    q.add(down);
                } 
            }
            if (isArrived) {
                break;
            }
            
            cnt++;
        }

        if (isArrived) {
            bw.write(cnt + "");
        } else {
            bw.write("use the stairs");
        }

        
        bw.flush();
        bw.close();

    }
}

