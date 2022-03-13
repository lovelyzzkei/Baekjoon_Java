import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.stream.Stream;

public class pb_15683 {

    static int n;
    static int m;
    static int[][] office;
    static int ans;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static boolean inRange(int y, int x) {
        return ((0 <= y && y < n) && (0 <= x && x < m)) ? true : false;
    }

    static void cctv() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                
                switch (office[i][j]) {
                    case 1: 
                    for (int d = 0; d < 4; d++) {
                        int y = i;
                        int x = j;
                        int[][] coors = new int[8][2];
                        int idx = 0;
                        while (inRange(y, x) && office[y][x] != 6) {
                            if (office[y][x] == 0) {
                                office[y][x] = -1;
                                coors[idx][0] = y;
                                coors[idx][1] = x;
                                idx += 1;
                            }
                            y += dy[d];
                            x += dx[d];
                        }
                        cctv();

                        for (int[] coor: coors) {
                            office[coor[0]][coor[1]] = 0;
                        }
                    }   
                    break;  

                    case 2: 
                    for (int d = 0; d < 4; d++) {
                        int y = i;
                        int x = j;
                        int[][] coors = new int[8][2];
                        int idx = 0;
                        while (inRange(y, x) && office[y][x] != 6) {
                            if (office[y][x] == 0) {
                                office[y][x] = -1;
                                coors[idx][0] = y;
                                coors[idx][1] = x;
                                idx += 1;
                            }
                            y += dy[d];
                            x += dx[d];
                        }
                        cctv();

                        for (int[] coor: coors) {
                            office[coor[0]][coor[1]] = 0;
                        }
                    }   
                    break;  
                }
            }
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 받기
        String[] t = br.readLine().split(" ");
        n = Integer.parseInt(t[0]);
        m = Integer.parseInt(t[1]);

        office = new int[n][m];
        for (int i = 0; i < n; i++) {
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < m; j++) {
                office[i][j] = input[j];
            }
        }

        // cctv를 찾아 재귀로 사각 지대의 크기를 구함
        cctv();
          

        bw.flush();
        bw.close();
    }
}
