import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_11967 {    

    static int n, m;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static boolean[][] visited, isLightOn;
    static HashMap<String, ArrayList<String>> barn;

    static boolean inRange(int y, int x) {
        return (0 < y && y <= n) && (0 < x && x <= n);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);


        barn = new HashMap<>();
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            String key = input[1] + " " + input[0];
            String value = input[3] + " " + input[2];

            if (barn.get(key) == null) {
                barn.put(key, new ArrayList<String>());
            } 
            barn.get(key).add(value);
        }

        Deque<String> qNewRoom = new ArrayDeque<>();
        qNewRoom.add("1 1");

        Deque<String> qVisitedRoom = new ArrayDeque<>();
        qVisitedRoom.add("1 1");

        visited = new boolean[n+1][n+1];
        visited[1][1] = true;

        isLightOn = new boolean[n+1][n+1];
        isLightOn[1][1] = true;

        int cnt = 1;
        while (!qNewRoom.isEmpty()) {

            int qSize = qNewRoom.size();
            // light on
            for (int t = 0; t < qSize; t++) {
                String currRule = qNewRoom.pop();
                if (barn.get(currRule) != null) {
                    for (String room : barn.get(currRule)) {
                        String[] tmp = room.split(" ");
                        int b = Integer.parseInt(tmp[0]);
                        int a = Integer.parseInt(tmp[1]);
                        if (!isLightOn[b][a]) {
                            isLightOn[b][a] = true;
                            cnt++;
                        }
                    }
                    
                }
            }

            // move
            qSize = qVisitedRoom.size();
            for (int t = 0; t < qSize; t++) {

                String currRule = qVisitedRoom.pop();
                String[] curr = currRule.split(" ");
                int y = Integer.parseInt(curr[0]);
                int x = Integer.parseInt(curr[1]);

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (inRange(ny, nx) && !visited[ny][nx] && isLightOn[ny][nx]) {
                        visited[ny][nx] = true;
                        qVisitedRoom.add(ny + " " + nx);
                        qNewRoom.add(ny + " " + nx);
                    } 
                }
                qVisitedRoom.add(currRule);

            }
            // System.out.println(qNewRoom.toString());
            // System.out.println(qVisitedRoom.toString());
            // for (boolean[] arr : isLightOn) {
            //     System.out.println(Arrays.toString(arr));
            // }
            // System.out.println();
            
        }

        bw.write(cnt + "");
        bw.flush();
        bw.close();

    }
}

