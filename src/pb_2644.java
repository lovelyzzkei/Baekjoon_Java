import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2644 {  

    static int n;
    static int start, end, ret;
    
    static boolean isRelative = false;
    static boolean[] visited;
    static boolean[][] family;

    static void dfs(int curr, int depth) {
        visited[curr] = true;
        
        if (curr == end) {
            isRelative = true;
            ret = depth;
        }
        else {
            for (int next = 1; next <= n; next++) {
                if (family[curr][next] && !visited[next]) {
                    dfs(next, depth+1);
                }
            }
        }
        
    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        start = Integer.parseInt(input[0]);
        end = Integer.parseInt(input[1]);

        visited = new boolean[n+1];
        family = new boolean[n+1][n+1];

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            family[x][y] = family[y][x] = true;
        }

        dfs(start, 0);
        if (isRelative) {
            bw.write(ret + ""); 
        } else {
            bw.write(-1 + "");
        }
        
        bw.flush();
        bw.close();

    }
}
