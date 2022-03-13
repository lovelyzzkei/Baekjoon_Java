import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_3190 {

    static int n;
    static boolean[][] visited;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};


    static boolean inRange(int y, int x) {
        if ((1 <= y && y <= n) && (1 <= x && x <= n)) {
            if (!visited[y][x]) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        // 사과의 정보 저장
        boolean[][] apple = new boolean[n+1][n+1];
        for (int i = 0; i < k; i++) {
            String[] input = br.readLine().split(" ");
            apple[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = true;
        }

        // 방향 변환 정보 저장
        int l = Integer.parseInt(br.readLine());

        String[] changeDir = new String[10001];
        Arrays.fill(changeDir, "");
        for (int i = 0; i < l; i++) {
            String[] input = br.readLine().split(" ");
            changeDir[Integer.parseInt(input[0])] = input[1];
        }


        // 뱀의 머리 정보 및 몸통의 좌표 정보를 담기 위한 덱
        int headX = 1;
        int headY = 1;
        Deque<Integer[]> snake = new ArrayDeque<>();
        snake.add(new Integer[] {0, 0});

        // 뱀이 보드에 어디에 위치해있는지를 저장하기 위한 배열
        visited = new boolean[n+1][n+1];
        for (boolean[] arr : visited) {
            Arrays.fill(arr, false);
        }


        // 머리가 보드 안에 위치해있고, 몸과 부딪히지 않았으면 계속 진행
        int time = 0;
        int dir = 0;

        while (inRange(headY, headX)) {

            // 일단 이동
            snake.add(new Integer[] {headY, headX});
            visited[headY][headX] = true;

            // 머리 위치에 사과가 없으면 꼬리 부분이 줄어들음(없어짐).
            if (!apple[headY][headX]) {
                Integer[] tail = snake.remove();
                visited[tail[0]][tail[1]] = false;
            } else {
                // 사과는 먹으면 없어져야한다..
                apple[headY][headX] = false;
            }

            // 현재 시간이 끝난 뒤에 방향 전환해야되면 방향 전환
            String nextDir = changeDir[time];
            if (!nextDir.equals("")) {
                if (nextDir.equals("D")) dir = (dir == 3) ? 0 : dir+1;
                else dir = (dir == 0) ? 3 : dir-1;
            }

            time++;
            headY += dy[dir];
            headX += dx[dir];

        }


        

        bw.write(time + "");
        bw.flush();
        bw.close();

    }
}
