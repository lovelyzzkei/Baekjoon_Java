import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_16947 {

    static int n;
    static ArrayList<ArrayList<Integer>> subway;
    static boolean[] visited, finished, isCycle;
    static boolean isSuccess = false;
    static Deque<Integer> stack = new ArrayDeque<>();

    static void findCycle(int curr, int prev) {
        visited[curr] = true;
        if (isSuccess) return;
        for (Integer next : subway.get(curr)) {
            if (next != prev) {
                if (!visited[next]) {
                    stack.add(next);
                    findCycle(next, curr);
                } else if (!isSuccess && visited[next] && !finished[next]) {
                    isCycle[curr] = isCycle[next] = true;
                    while (!stack.isEmpty() && stack.getLast() != next) {
                        Integer top = stack.removeLast();
                        isCycle[top] = true;
                        finished[top] = true;
                    }
                    isSuccess = true;
                    return;
                }
            }
        }
        if (isSuccess) return;
        if (!finished[curr]) {
            finished[curr] = true;
            stack.removeLast();
        }
    }

    static void dfs(int curr, int dist) {
        visited[curr] = true;

        if (isSuccess) return;
        if (isCycle[curr]) {
            System.out.print(dist + " ");
            isSuccess = true;
            return;
        }

        for (Integer next : subway.get(curr)) {
            if (!visited[next]) {
                dfs(next, dist+1);
            }
        }
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        n = Integer.parseInt(br.readLine());

        subway = new ArrayList<>();
        for (int i = 0; i<= n; i++) {
            subway.add(new ArrayList<>());
        }


        
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            subway.get(u).add(v);
            subway.get(v).add(u);
        }

        visited = new boolean[n+1];     // 정점의 방문여부
        finished = new boolean[n+1];    // 정점의 탐색 완료 여부
        isCycle= new boolean[n+1];      // 정점의 순환선 포함 여부

        stack.add(1);
        findCycle(1, 0);

        for (int i = 1; i <= n; i++) {
            Arrays.fill(visited, false);
            isSuccess = false;
            if (isCycle[i]) System.out.print(0 + " ");
            else dfs(i, 0);
        }       
        
        bw.flush();
        bw.close();

    }
}

