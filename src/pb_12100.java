import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Stream;


public class pb_12100 {


    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int ans = 0;
    static int n;

    static boolean inRange(int y, int x) {
        return ((0 <= y && y < n) && (0 <= x && x< n)) ? true : false;
    }

    // numRound : 지금 몇번째 이동인지
    // board : 현재 게임판의 상태
    static void game(int numRound, int[][] board){

        
        // 5번 이동을 모두 하면 종료하고 보드에서 가장 큰 값을 확인
        if (numRound > 5){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(ans, board[i][j]);
                }
            }
            return;
        }

        // 4방향 모두 확인을 해야함
        for (int d = 0; d < 4; d++) {

            switch (d) {
                case 0:
                    // 각 방향에 대해 이동을 하면 블럭을 합쳐야함
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++){
                            int x = j;
                            int y = i;
                            int val = board[i][j];

                            while (true) {
                                x += dx[d];
                                y += dy[d];
            

                                // 다음 좌표가 보드 안에 없으면 역시 그 이상 이동 불가
                                if (!inRange(y, x)) {
                                    board[i][j] = 0;
                                    board[y-dy[d]][x-dx[d]] = val;
                                    break;
                                }
                                // 다음 좌표를 확인했을 때, 현재 자신의 값과 같으면 합치기. 그리고 종료
                                else if (board[y][x] == board[i][j]) {
                                    board[y][x] *= 2;
                                    board[i][j] = 0;
                                    break;
                                } else if (board[y][x] != 0 || !inRange(y, x)) { 
                                    // 다음 좌표가 0이 아니고 내 값도 아니면 그 이상 이동이 불가
                                    board[i][j] = 0;
                                    board[y-dy[d]][x-dx[d]] = val;
                                    break;
                                } 
                            }
                        }
                    }

                    // 각 경우의 수마다 4방향을 모두 확인해야하므로 재귀로 구현
                    game(numRound + 1, board);  
                    break;        
                
                case 1:
                    // 각 방향에 대해 이동을 하면 블럭을 합쳐야함
                    for (int i = 0; i < n; i++) {
                        for (int j = n-1; j >= 0; j--){
                            int x = j;
                            int y = i;
                            int val = board[i][j];

                            while (true) {
                                x += dx[d];
                                y += dy[d];
            

                                // 다음 좌표가 보드 안에 없으면 역시 그 이상 이동 불가
                                if (!inRange(y, x)) {
                                    board[i][j] = 0;
                                    board[y-dy[d]][x-dx[d]] = val;
                                    break;
                                }
                                // 다음 좌표를 확인했을 때, 현재 자신의 값과 같으면 합치기. 그리고 종료
                                else if (board[y][x] == board[i][j]) {
                                    board[y][x] *= 2;
                                    board[i][j] = 0;
                                    break;
                                } else if (board[y][x] != 0 || !inRange(y, x)) { 
                                    // 다음 좌표가 0이 아니고 내 값도 아니면 그 이상 이동이 불가
                                    board[i][j] = 0;
                                    board[y-dy[d]][x-dx[d]] = val;
                                    break;
                                } 
                            }
                        }
                    }

                    // 각 경우의 수마다 4방향을 모두 확인해야하므로 재귀로 구현
                    game(numRound + 1, board);  
                    break; 

                case 2:
                    // 각 방향에 대해 이동을 하면 블럭을 합쳐야함
                    for (int i = n-1; i >= 0; i--) {
                        for (int j = 0; j < n; j++){
                            int x = j;
                            int y = i;
                            int val = board[i][j];

                            while (true) {
                                x += dx[d];
                                y += dy[d];
            

                                // 다음 좌표가 보드 안에 없으면 역시 그 이상 이동 불가
                                if (!inRange(y, x)) {
                                    board[i][j] = 0;
                                    board[y-dy[d]][x-dx[d]] = val;
                                    break;
                                }
                                // 다음 좌표를 확인했을 때, 현재 자신의 값과 같으면 합치기. 그리고 종료
                                else if (board[y][x] == board[i][j]) {
                                    board[y][x] *= 2;
                                    board[i][j] = 0;
                                    break;
                                } else if (board[y][x] != 0 || !inRange(y, x)) { 
                                    // 다음 좌표가 0이 아니고 내 값도 아니면 그 이상 이동이 불가
                                    board[i][j] = 0;
                                    board[y-dy[d]][x-dx[d]] = val;
                                    break;
                                } 
                            }
                        }
                    }

                    // 각 경우의 수마다 4방향을 모두 확인해야하므로 재귀로 구현
                    game(numRound + 1, board);  
                    break; 

                case 3:
                    // 각 방향에 대해 이동을 하면 블럭을 합쳐야함
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++){
                            int x = j;
                            int y = i;
                            int val = board[i][j];

                            while (true) {
                                x += dx[d];
                                y += dy[d];
            

                                // 다음 좌표가 보드 안에 없으면 역시 그 이상 이동 불가
                                if (!inRange(y, x)) {
                                    board[i][j] = 0;
                                    board[y-dy[d]][x-dx[d]] = val;
                                    break;
                                }
                                // 다음 좌표를 확인했을 때, 현재 자신의 값과 같으면 합치기. 그리고 종료
                                else if (board[y][x] == board[i][j]) {
                                    board[y][x] *= 2;
                                    board[i][j] = 0;
                                    break;
                                } else if (board[y][x] != 0 || !inRange(y, x)) { 
                                    // 다음 좌표가 0이 아니고 내 값도 아니면 그 이상 이동이 불가
                                    board[i][j] = 0;
                                    board[y-dy[d]][x-dx[d]] = val;
                                    break;
                                } 
                            }
                        }
                    }

                    // 각 경우의 수마다 4방향을 모두 확인해야하므로 재귀로 구현
                    game(numRound + 1, board);  
                    break; 
            }
            
           
        }

    }

    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 받기
        n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                board[i][j] = input[j];
            }
        }

        // 재귀로 구현
        game(1, board);
        bw.write(String.format("%d", ans));
        bw.flush();
        bw.close();
    }
}
