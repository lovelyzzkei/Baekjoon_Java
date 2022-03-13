import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2213 {

    static int n;                                       // 전체 트리의 정점의 수
    static int[] value;                                 // 각 정점의 가중치를 저장
    static ArrayList<ArrayList<Integer>> adj, child;    // 인접한 노드와 트리로 만들었을 때 각 노드의 자식 노드들을 저장
    static int[][] cache;                               // 메모이제이션을 위한 배열
    static ArrayList<Integer> picked;                              // 최대 독립집합을 이루는 노드들을 저장
    static boolean[] visited;                           // DFS에서 각 정점의 방문여부를 체크하기 위한 배열

    // 트리를 생성하기 위한 DFS
    static void dfs(int node) {
        visited[node] = true;
        for (int next : adj.get(node)) {
            if (!visited[next]) {
                child.get(node).add(next);
                dfs(next);
            }
        }
    }


    // 최대 독립 집합의 크기 구함
    // int indieSet(int node, int parentPicked) : 정점 node에서 자신의 parent가 parentPicked(0/1)일때 
    // 본인을 포함하는 subtree에서의 최대 독립 집합의 크기
    static int indieSet(int node, int parentPicked) {
        if (cache[node][parentPicked] != -1) return cache[node][parentPicked];

        int pick = 0;        // 자신을 pick 하는 경우
        int notPick = 0;    // 자신을 pick 하지 않는 경우

        // parent의 여부에 상관없이 자신을 pick하지 않는 경우는 항상 존재
        for (int next : child.get(node)) {
            notPick += indieSet(next, 0);
        }
        
        // parent가 독립집합에 포함이 안되는 경우 -> 가능한 두 경우 중 최댓값을 반환    
        if (parentPicked == 0) {
            pick = value[node];
            for (int next : child.get(node)) {
                pick += indieSet(next, 1);
            }
        }
        return cache[node][parentPicked] = Math.max(cache[node][parentPicked], Math.max(pick, notPick));
    }

    static void trackAnswer(int node, int parentPicked) {

        int pick = 0;        // 자신을 pick 하는 경우
        int notPick = 0;    // 자신을 pick 하지 않는 경우

        // parent의 여부에 상관없이 자신을 pick하지 않는 경우는 항상 존재
        for (int next : child.get(node)) {
            notPick += indieSet(next, 0);
        }
        
        // parent가 독립집합에 포함이 안되는 경우 -> 가능한 두 경우 중 최댓값을 반환    
        if (parentPicked == 0) {
            pick = value[node];
            for (int next : child.get(node)) {
                pick += indieSet(next, 1);
            }
        }

        if (notPick < pick) {
            picked.add(node);
            for (int next : child.get(node)) {
                trackAnswer(next, 1);
            }
        }

        else {
            for (int next : child.get(node)) {
                trackAnswer(next, 0);
            }
        }
    }


    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 입력 받기
        n = Integer.parseInt(br.readLine());

        // 각 정점의 가중치 입력 받기
        value = Stream.of(("0 "+br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        // adj 초기화
        adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<Integer>());
        }

        // 간선의 정보 받아 adj에 저장
        for (int i = 0; i < n-1; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // 루트를 1로 잡고 트리 생성
        child = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= n; i++) {
            child.add(new ArrayList<Integer>());
        }
        visited = new boolean[n+1];
        dfs(1);

        // DP를 사용하여 최대 독립집합의 크기를 구함
        cache = new int[n+1][2];
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }
        int ret = indieSet(1, 0);
        bw.write(ret + "\n");


        // 최대 독립 집합 출력
        picked = new ArrayList<Integer>();
        trackAnswer(1, 0);
        Collections.sort(picked);
        for (int num : picked) {
            bw.write(num + " ");
        }
        bw.flush();
        bw.close();

    }
}
