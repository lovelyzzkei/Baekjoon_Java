import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1795 {    

    static int n, m;
    static int ny, nx;
    static int[][] board;
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};

    static boolean[][] visited;
    static Deque<Integer[]> nextCoor;

    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < m);
    }

    static void calcNextCoor(int y, int x, int k) {
        if (k == 0) {

            return;
        }
        for (int i = 0; i < 8; i++) {
            if (inRange(y+dy[i], x+dx[i])) {
                nextCoor.add(new Integer[] {y+dy[i], x+dx[i]});
                calcNextCoor(y+dy[i], x+dx[i], k-1);
            }
            
        }
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);


        Deque<Integer[]> q = new ArrayDeque<>();
        Deque<HashMap<Integer[], Boolean>> qVisited = new ArrayDeque<>();
        // visited = new boolean[n][m];


        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] inputArr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (inputArr[j] == '.') board[i][j] = 0; 
                else {
                    board[i][j] = inputArr[j] - '0';
                    q.add(new Integer[] {i, j, board[i][j]});   // [y, x, k값]
                }
            }
        }


        int move = 0;
        boolean isOne = false;

        while (!q.isEmpty()) {

            int qSize = q.size();

            if (qSize == 1) {
                isOne = true;
                break;
            }

            for (int t = 0; t < qSize; t++) {

                Integer[] curr = q.pop();
                int y = curr[0]; int x = curr[1]; int k = curr[2];


                // HashMap<Integer[], Boolean> visited = qVisited.pop();
                nextCoor = new ArrayDeque<>();
                
                calcNextCoor(y, x, k);

                for (Integer[] arr : nextCoor) {
                    System.out.print(Arrays.toString(arr) + " ");
                }
                
                
            }

            move++;

        }



        bw.flush();
        bw.close();

    }
}

