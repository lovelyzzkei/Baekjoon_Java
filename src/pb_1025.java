import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class pb_1025 {


    static int n;
    static int m;
    static String table[][];
    static double ans = -1;

    static boolean inRange(int y, int x) {
        return ((0 <= y && y < n) && (0 <= x && x < m)) ? true : false;
    }

    static void findSquare(int y, int x) {

        // dy와 dx를 각각 행과 열의 공차라고 생각, 각각의 공차에 대해 확인
        for (int dy = -n; dy < n; dy++) {
            for (int dx = -m; dx < m; dx++) {
                String num = table[y][x];
                int ny = y + dy;
                int nx = x + dx;
                while (inRange(ny, nx) && (dy != 0 || dx != 0)) {
                    num += table[ny][nx];
                    ny += dy;
                    nx += dx;

                    // 수를 만들었으면 해당 수가 완전 제곱수인지 확인
                    Double isSquare = Math.sqrt(Integer.parseInt(num));
                    if (isSquare == isSquare.intValue()) {
                        ans = Math.max(ans, Math.pow(isSquare, 2));
                    }
                }
            }
        }
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        // 1. 입력 받기
        String input[] = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        table = new String[n][m];
        for (int i = 0; i < n; i++) {
            String temp = br.readLine().trim();
            for (int j = 0; j < m; j++) {
                table[i][j] = temp.substring(j, j+1);
            }
        }

        
        // 2. 각 칸의 숫자가 제곱수인지 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Double num = Math.sqrt(Integer.parseInt(table[i][j]));
                
                if (num == num.intValue()) {
                    ans = Math.max(ans, Math.pow(num, 2));
                }
            }
        }

        // 3. 각 칸마다 열과 행에 등차 수열 관계를 적용하여 수를 구하고 제곱수를 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                findSquare(i, j);
            }
        }

        bw.write(String.format("%d", (int) ans));    
        bw.flush();
        bw.close();
    }
}
