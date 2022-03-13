import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_10265 {

    static int n, k;
    static int[] rule, depth;
    static int[][] cache;
    static boolean[] visited, finished, cycle;

    static int dfs(int curr) {
        visited[curr] = true;
        
        int next = rule[curr];
        depth[curr] = 0;
        if (visited[next]) {
            if (!finished[next]) {
                for (int tmp = next; tmp != curr; tmp = rule[tmp]) {
                    cycle[tmp] = true;
                    depth[curr]++;
                }
                cycle[curr] = true;
                depth[curr]++;
            } else {
                depth[curr] += 1 + depth[next];
            }
        } else {
            depth[curr] += dfs(next);
            if (cycle[curr] && cycle[next]) {
                depth[curr] = depth[next];
            } else if (!cycle[curr] && cycle[next]) {
                depth[curr] = 1 + depth[next];
            }
            else {
                depth[curr]++;
            }
        }
        finished[curr] = true;
        
        
        return depth[curr];
    }


    static int realDepth(int curr) {
        visited[curr] = true;
        int next = rule[curr];
        int size = 0;
        if (cycle[curr]) {
            size = depth[curr];
        }
        
        else if (!visited[next]) {
            size += 1 + realDepth(next);
        } 
        else {
            size = 1;
        }
        return size;
        
    }

    static int calcPerson(int curr, int capacity) {
        if (capacity < 0 || curr == n + 1) return 0;
        
        if (cache[curr][capacity] != -1) return cache[curr][capacity];

        cache[curr][capacity] = calcPerson(curr+1, capacity);
        System.out.println(curr + " " + capacity);

        if (!visited[curr]) {
            int numOfPerson = realDepth(curr);

            if (capacity >= numOfPerson) {
                cache[curr][capacity] = Math.max(cache[curr][capacity], calcPerson(curr+1, capacity-numOfPerson) + numOfPerson);
            }
        }
        

        for (int[] arr : cache) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
        
        return cache[curr][capacity];
    }

    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        
        rule = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        depth = new int[n+1];
        visited = new boolean[n+1];
        finished = new boolean[n+1];
        cycle = new boolean[n+1];

        Arrays.fill(depth, -1);
        

        ArrayList<Integer> scc = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                scc.add(dfs(i));
            }
        }

        System.out.println(Arrays.toString(depth));
        System.out.println(Arrays.toString(cycle));

        Collections.sort(scc);

        Arrays.fill(visited, false);
        cache = new int[n+1][k+1];
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }

        int ret = calcPerson(1, k);
        
        bw.write(ret+"");
        bw.flush();
        bw.close();

    }
}
