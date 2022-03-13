import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_17299 { 


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] F = new int[1_000_001];

        // 각 수가 등장한 횟수를 저장
        for (int i = 0; i < n; i++) {
            F[A[i]]++;
        }

        // 각 수를 돌면서 현재의 F가 스택의 맨 위에 있는 F보다 작으면 그대로 push
        // 그렇지 않으면 그렇게 될 때까지 pop, 이때 pop된 수들의 NGF가 현재 수
        int[] NGF = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(0);

        for (int i = 1; i < n; i++) {
            int top = F[A[stack.getLast()]];
            int curr = F[A[i]];

            if (top < curr) {
                while (!stack.isEmpty() && F[A[stack.getLast()]] < F[A[i]]) {
                    int idx = stack.removeLast();
                    NGF[idx] = A[i];
                }
            }
            stack.add(i);
        }

        while (!stack.isEmpty()) {
            NGF[stack.removeLast()] = -1;
        }

        for (int i : NGF) {
            bw.write(i + " ");
        }

        

        bw.flush();
        bw.close();

    }
}
