import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_16940 {    

    static int n;
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < n-1; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        int[] result = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);

        visited = new boolean[n+1];
        visited[1] = true;

        int start = 0;  // 해당 level의 시작 idx
        int end = 0;    // 해당 level의 끝 idx
        boolean isProperBFS = true;

        while (!q.isEmpty()) {

            int qSize = q.size();
            for (int t = 0; t < qSize; t++) {
                int curr = q.pop();

                int cnt = 0;
                StringBuffer sb = new StringBuffer();

                for (int next : tree.get(curr)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                        sb.append(next+" ");
                        cnt++;
                    }
                }

                
                // 주어진 방문 순서의 각 레벨을 정렬했을 때, 큐에 있는 원소를 정렬한 것과 같다면 올바른 방문 순서
                System.out.println(q.toString());
                
                if (cnt != 0) {

                    int[] subResultArr = new int[cnt];
                    System.arraycopy(result, start+qSize, subResultArr, 0, cnt);
                    Arrays.sort(subResultArr);

                    int[] qArr = Stream.of(sb.toString().split(" ")).mapToInt(Integer::parseInt).toArray();
                    Arrays.sort(qArr);

                    System.out.println(Arrays.toString(subResultArr) + " " + Arrays.toString(qArr));

                    if (!Arrays.equals(subResultArr, qArr)) {
                        isProperBFS = false;
                        break;
                    }
                    start++;
                }

            }

            if (!isProperBFS) break;
        }

        bw.write((isProperBFS ? 1 : 0) + "");
        bw.flush();
        bw.close();

    }
}

