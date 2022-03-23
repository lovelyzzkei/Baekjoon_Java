import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_16437 {

    static int n;
    static int[] island;
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] visited;
   
    static int getSheep(int curr) {
        visited[curr] = true;

        int cnt = island[curr];
        for (int next : tree.get(curr)) {
            if (!visited[next]) {
                if (island[next] > 0) cnt += getSheep(next);
                else {
                    int cand = getSheep(next);
                    cnt += (cand > 0) ? cand : 0;
                }
            }
        }
        return cnt;
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        
        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        island = new int[n+1];
        for (int i = 2; i < n+1; i++) {
            String[] input = br.readLine().split(" ");
            int num = Integer.parseInt(input[1]);
            int u = Integer.parseInt(input[2]);

            tree.get(i).add(u);
            tree.get(u).add(i);

            if (input[0].equals("S")) island[i] = num; 
            else island[i] = -num;
        }

        int root = 1;
        visited = new boolean[n+1];
        int ret = getSheep(root);
        
        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
