import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_1595 { 

    static int n;
    static ArrayList<ArrayList<Integer[]>> dist;
    static boolean[] visited;

    static int dfs(int curr) {
        visited[curr] = true;

        int d = 0;
        for (Integer[] next : dist.get(curr)) {
            if (!visited[next[0]]) {
                d = Math.max(d, next[1] + dfs(next[0]));
            }
        }
        return d;
    }
    
    


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        dist = new ArrayList<>();
        visited = new boolean[10001];

        for (int i = 0; i <= 10000; i++) {
            dist.add(new ArrayList<>());
        }

        // 거리 정보 저장
        String input = br.readLine();
        n = 0;
        while (input != null && !input.equals("")) {
        
            String[] inputArr = input.split(" ");
            
            int u = Integer.parseInt(inputArr[0]);
            int v = Integer.parseInt(inputArr[1]);
            int d = Integer.parseInt(inputArr[2]);

            dist.get(u).add(new Integer[]{v, d});
            dist.get(v).add(new Integer[]{u, d});

            n = Math.max(n, Math.max(u, v));
            input = br.readLine();
        }

        int ret = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(visited, false);
            ret = Math.max(ret, dfs(i));
        }


        
        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
