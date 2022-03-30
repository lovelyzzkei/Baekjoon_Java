import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_20182 {

    static int n, m, a, b, c;
    static int[] node;
    static ArrayList<ArrayList<Integer[]>> graph;
    static final int INF = 2_000_001;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        a = Integer.parseInt(input[2]);
        b = Integer.parseInt(input[3]);
        c = Integer.parseInt(input[4]);

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]);

            graph.get(u).add(new Integer[] {v, d});
            graph.get(v).add(new Integer[] {u, d});
        }


        // [현재 경로의 골목의 요금의 최댓값 중 최솟값, 현재 교차로, 현재 가진 돈]
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b)->(a[0] - b[0]));
        pq.add(new Integer[] {0, a, c});  
        
        node = new int[n+1];
        Arrays.fill(node, INF);
        node[a] = 0;

        int ret = -1;
        while (!pq.isEmpty()) {
            Integer[] curr = pq.poll();
            int currMin = curr[0];
            int currNode = curr[1];
            int currMoney = curr[2];

            if (currNode == b) {
                ret = currMin;
                break;
            }

            for (Integer[] next : graph.get(currNode)) {
                int nextNode = next[0];
                int cost = next[1];
                int dist = Math.max(cost, currMin);
                if (dist < node[nextNode] && cost <= currMoney) {
                    node[nextNode] = dist;
                    pq.add(new Integer[] {dist, nextNode, currMoney - cost});
                }
            }
        }
        

        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
