import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class pb_14500 {

    static int[] dy = {0, -1, 0};
    static int[] dx = {1, 0, -1};
    static int n, m;
    static int nums[][];
    static int ans = 0;
    
    static void tetromino(int y, int x, int numBlock, int sum, int py, int px){
        if (numBlock == 4) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < 3; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            // 다음 블럭이 종이 위에 있으면 다음 블럭으로 나아감
            // 같은 좌표를 밟아서는 안됨
            if ((0 <= ny && ny < n) && (0 <= nx && nx < m) && (ny != py || nx != px)) {
                tetromino(ny, nx, numBlock + 1, sum + nums[ny][nx], y, x);
            }
        }
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // n과 m 그리고 종이에 적혀있는 수들을 받기
        String input[] = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        nums = new int[n][m];
        for (int i = 0; i < n; i++){
            String temp[] = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                nums[i][j] = Integer.parseInt(temp[j]);
            }
        }


        // ㅗ, ㅏ, ㅓ, ㅜ 모양의 테트로미노 처리
        int[][][] exceptions = {
            {{1, 0}, {0, -1}, {-1, 0}},
            {{1, 0}, {0, 1}, {-1, 0}},
            {{1, 0}, {0, -1}, {0, 1}},
            {{-1, 0}, {0, -1}, {0, 1}}
        };
        
        // 각 칸마다 테트로미노를 재귀로 생성하여 값을 확인, 최댓값을 찾자
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // 1. ㅗ, ㅏ, ㅓ, ㅜ에서 최댓값이 있는지 확인
                // 각 모양에서
                for (int k = 0; k < 4; k++) {
                    int sum = nums[i][j];

                    // 주변을 둘러싼 세개의 블럭이 모두 종이 안에 있으면 해당 수들의 합이 최댓값인지 확인 
                    for (int l = 0; l < 3; l++) {
                        int ny = i + exceptions[k][l][0];
                        int nx = j + exceptions[k][l][1];
                        if ((0 <= ny && ny < n) && (0 <= nx && nx < m)) {
                            sum += nums[ny][nx];
                        } else {
                            break;
                        }
                    }
                    ans = Math.max(ans, sum);
                }

                // 2. 이외의 조각들에 최댓값이 있는지 확인
                tetromino(i, j, 1, nums[i][j], -1, -1);
            }
        }


        bw.write(String.format("%d", ans));
        bw.flush();
        bw.close();
    }
}
