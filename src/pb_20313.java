import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_20313 {

    static int n, m, a, b, k;
    static int[][] magic;
    static long[] dist;
    static ArrayList<ArrayList<Integer[]>> graph;
    static final long INF = 3_000_000_000_001L;
    

    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        a = Integer.parseInt(input[2]);
        b = Integer.parseInt(input[3]);

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        magic = new int[101][m+1];

        for (int i = 1; i <= m; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int t = Integer.parseInt(input[2]);

            // [도착 노드, 도로 번호]
            magic[0][i] = t;
            graph.get(u).add(new Integer[] {v, i});
            graph.get(v).add(new Integer[] {u, i});
        }

        long ret = INF;
        k = Integer.parseInt(br.readLine());

        for (int i = 1; i <= k; i++) {
            int[] arr = Stream.of(("0 "+br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j <= m; j++) {
                magic[i][j] = arr[j];
            }
        }   
        
        
        PriorityQueue<Long[]> pq = new PriorityQueue<>(new Comparator<Long[]>() {
            @Override
            public int compare(Long[] o1, Long[] o2) {
                return o1[0] < o2[0] ? -1 : 1;
            }
        });
        pq.add(new Long[]{(long)0, (long)a, (long)0});  // [거리, 현재 노드, 마법사용횟수]  

        dist = new long[n+1];
        Arrays.fill(dist, INF); 

        while (!pq.isEmpty()) {
            Long[] curr = pq.poll();
            long currDist = curr[0];
            int currNode = (int)(long)curr[1];
            int currMagic = (int)(long)curr[2];

            if (currNode == b) {
                ret = currDist;
                break;
            }

            if (dist[currNode] < currDist) continue;
            for (Integer[] next : graph.get(currNode)) {
                int nextNode = next[0];
                int nextRoad = next[1];
                int cost = magic[currMagic][nextRoad];
                boolean spellMagic = false;

                if (k != 0 && currMagic < k && magic[currMagic+1][nextRoad] < cost) {
                    cost = magic[currMagic+1][nextRoad];
                    spellMagic = true;
                }

                long nextDist = currDist + cost;
                if (nextDist < dist[nextNode]) {
                    dist[nextNode] = nextDist;
                    pq.add(new Long[] {nextDist, (long)nextNode, (long)(spellMagic ? currMagic+1 : currMagic)});
                }
            }

            // for (Long[] arr : pq) {
            //     System.out.print(Arrays.toString(arr) + " ");
            // }
            // System.out.println();
        }
        

        
        bw.write(ret + "");
        bw.flush();
        bw.close();
    }
}
