import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2304 {
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] rods = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            rods[i] = new int[] {Integer.parseInt(input[0]), Integer.parseInt(input[1])};
        }

        // 기둥들을 위치 순으로 정렬
        Arrays.sort(rods, Comparator.comparingInt(a->a[0]));

        // 각 기둥의 인덱스를 저장할 스택을 덱으로 구현
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(0);


        int ret = 0;
        int maxHeight = rods[stack.getLast()][1];

        for (int i = 0; i < n; i++) {
            int posX = rods[i][0];
            int height = rods[i][1];
            int formalHeight = rods[stack.getLast()][1];

            // 만약 현재 기둥이 이전의 기둥들보다 크면 이전 기둥의 가장 큰 높이로 다각형을 만듬
            // + 높이가 계속 커져야됨
            if (formalHeight < height && maxHeight < height) {
                int posLeft = 0;
                while (!stack.isEmpty()) {
                    posLeft = stack.removeLast();
                }
                posLeft = rods[posLeft][0];
                ret += (posX - posLeft) * maxHeight;

                maxHeight = height;
            } 
            stack.add(i);
            
        }

        // 마지막에 스택이 비어있지 않을 경우는 높이가 작아지는 경우
        // while문을 돌리면서 순차적으로 다각형 넓이 계산
        while (!stack.isEmpty()) {
            int now = stack.getLast();
            int posX = rods[now][0];
            int height = rods[now][1];

            int top = 0;
            while (!stack.isEmpty() && rods[stack.getLast()][1] <= height) {
                top = stack.removeLast();
            }

            
            if (!stack.isEmpty()) {
                top = stack.getLast();
            } else {
                posX += 1;
            }
            int posLeft = rods[top][0];
            ret += Math.max(1, posX-posLeft) * height;
        }

        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
