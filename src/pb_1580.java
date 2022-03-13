import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1580 {    

    static int n, m;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < m);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        // A의 좌표
        int ay = 0;
        int ax = 0;

        // B의 좌표
        int by = 0;
        int bx = 0;

        // 게임판의 정보 저장. A와 B의 좌표를 얻음
        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    ay = i; ax = j;
                }
                if (board[i][j] == 'B') {
                    by = i; bx = j;
                }
            }
        }
        System.out.println(ay + " " + ax + " " + by + " " + bx);

        // BFS를 수행할 큐, [A좌표, B좌표] 형식으로 저장
        Deque<Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[] {ay, ax, by, bx});

        // 방문 정보를 저장할 배열. 두 플레이어의 방문 정보를 따로 저장
        Deque<HashMap<Integer[], Boolean>> qVisitedA = new ArrayDeque<>();
        Deque<HashMap<Integer[], Boolean>> qVisitedB = new ArrayDeque<>();

        HashMap<Integer[], Boolean> visitedA = new HashMap<>();
        visitedA.put(new Integer[] {ay, ax}, true);
        qVisitedA.add(visitedA);

        HashMap<Integer[], Boolean> visitedB = new HashMap<>();
        visitedB.put(new Integer[] {by, bx}, true);
        qVisitedB.add(visitedB);
        
        // boolean[][] visitedA = new boolean[n][m];
        // boolean[][] visitedB = new boolean[n][m];
        
        // visitedA[ay][ax] = true;
        // visitedB[by][bx] = true;

        int move = 0;
        boolean isChanged = false;

        // BFS 수행
        // 최소 턴이면 일단 최대한 두 플레이어가 한턴에 모두 움직이는 것이 유리
        // 기본적으로는 두 플레이어 모두가 움직이는 것으로 처리하고
        // 두 플레이어가 동시에 움직일 수 없을때만(교차하는 경우) 하나씩 움직임
        while (!q.isEmpty()) {

            int qSize = q.size();
            for (int k = 0; k < qSize; k++) {

                Integer[] curr = q.pop();
                ay = curr[0]; ax = curr[1]; by = curr[2]; bx = curr[3];
                visitedA = qVisitedA.pop();
                visitedB = qVisitedB.pop();

                // 서로 위치가 바뀌었으면 종료
                if (board[ay][ax] == 'B' && board[by][bx] == 'A') {
                    isChanged = true;
                    break;
                }

                // 먼저 a를 움직이고
                for (int i = 0; i < 8; i++) {
                    int nay = ay + dy[i];
                    int nax = ax + dx[i];
                    
                    Integer[] nextA = new Integer[] {nay, nax};

                    if (inRange(nay, nax) && visitedA.get(nextA) == null && board[nay][nax] != 'X') {

                        // 동시에 b를 움직임
                        for (int j = 0; j < 8; j++) {
                            int nby = by + dy[j];
                            int nbx = bx + dx[j];

                            Integer[] nextB = new Integer[] {nby, nbx};

                            // 두 플레이어의 좌표가 같은 경우 거르기
                            if (inRange(nby, nbx) && visitedB.get(nextB) == null && board[nby][nbx] != 'X' && !(nby == nay && nbx == nax)) {

                                if (nay != by || nax != bx || nby != ay || nbx != ax) {

                                    // 두 플레이어가 교차되지 않는 경우 두 플레이어 모두 움직임
                                    visitedA.put(nextA, true);
                                    visitedB.put(nextB, true);
                                    q.add(new Integer[] {nay, nax, nby, nbx});
                                    qVisitedA.add(visitedA);
                                    qVisitedB.add(visitedB);

                                } 
                                
                                else {

                                    // 교차되는 경우에는 둘중 하나만 움직임. A가 움직인다고 생각
                                    visitedA.put(nextA, true);
                                    q.add(new Integer[] {nay, nax, by, bx});
                                    qVisitedA.add(visitedA);
                                    qVisitedB.add(visitedB);

                                }
                            }

                        }

                    }

                }

                for (Integer[] arr : q) {
                    System.out.print(Arrays.toString(arr) + " ");
                }
                System.out.println();

                for (HashMap<Integer[], Boolean> arr : qVisitedA) {
                    for (Integer[] key : arr.keySet()) {
                        System.out.print(Arrays.toString(key) + " ");
                    }
                    System.out.println();
                }
                for (HashMap<Integer[], Boolean> arr : qVisitedB) {
                    for (Integer[] key : arr.keySet()) {
                        System.out.print(Arrays.toString(key) + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }

            if (isChanged) break;
            move++;
        }
        if (isChanged) bw.write(move + "");
        else bw.write(-1 + "");
        bw.flush();
        bw.close();

    }
}

