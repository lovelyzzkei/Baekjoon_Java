import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class test {

    static int n;
    static int[] island;
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] visited;
   
    // 루트에서 시작하여 리프노드까지 내려감
    // 이때 내려가면서 가는 길에 있는 늑대들의 수를 모두 더해줌
    // 만약 현재의 섬에 있는 양의 수가 늑대의 수보다 많으면 그 차이만큼 반환
    // 그렇지 않을 경우 0을 반환
    static long getSheep(int curr, long numOfWolves) {
        visited[curr] = true;

        long animal = island[curr];
        long cnt = (animal - numOfWolves > 0) ? animal-numOfWolves : 0;

        for (int next : tree.get(curr)) {
            if (!visited[next]) {
                long nextWolves = (animal < 0) ? -animal : 0;
                numOfWolves += nextWolves;
                cnt += getSheep(next, numOfWolves);
                numOfWolves -= nextWolves;
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
        long ret = getSheep(root, 0);
        
        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
