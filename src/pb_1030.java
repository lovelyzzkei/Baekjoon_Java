import java.io.*;
import java.util.*;

public class pb_1030 {

    static int ans[][];   // 결과값 담는 배열
    static int s, N, K, r1, r2, c1, c2;

    /*
    y, x : 전체 평면에서 봤을 때 현재 정사각형의 y좌표, x좌표
    time : 현재 시간
    color : 현재 정사각형의 색. 0 : 흰색, 1 : 검은색   
    */ 
    static void makeFractal(int y, int x, int time, int color){
        int fractal[][] = new int[N][N];

        // N x N 프랙탈 채우기
        if (color == 1) {
            for (int[] i : fractal) {
                Arrays.fill(i, 1);
            }
        } else {

            int start = (N-K)/2;
            int end = start + K;

            for (int i = start; i < end; i++) {
                for (int j = start; j < end; j++) {
                    fractal[i][j] = 1;
                }
            }
        }

        // 시간이 되었으면 해당 프랙탈이 입력에서 주어진 범위에 있는지 확인
        // 맞다면 해당 부분을 ans 배열에 저장.
        // 시간이 안되었다면 계속 증식
        if (time == s) {
            int starty, endy, startx, endx;

            if (y <= r1 && r1 < y + N) {
                starty = r1 - y;
                if (y <= r2 && r2 < y + N) {
                    endy = r2 - y;
                } else {
                    endy = N;
                }
            } else {
                if (r2 > y + N) {
                    starty = (r1 < y) ? 0 : -1;
                    endy = (r1 < y) ? N : -1;
                } else {
                    starty = (r1);
                }
                starty = endy = -1;
            }

            if (x <= c1 && c1 < x + N) {
                startx = c1 - x;
                if (x <= c2 && c2 < x + N) {
                    endx = c2 - x;
                } else {
                    endx = N;
                }
            } else {
                startx = endx = -1;
            }

            System.out.println(String.format("%d %d %d %d %d %d", y, x, starty, endy, startx, endx));

            for (int i = starty; i < endy; i++) {
                for (int j = startx; j < endx; j++) {
                    System.out.print(fractal[i][j]);
                    ans[i-y][j-x] = fractal[i][j];
                }
                System.out.println();
            }
        
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (fractal[i][j] == 0) {
                        makeFractal(y+i*N, x+j*N, time+1, 0);
                    } else {
                        makeFractal(y+i*N, x+j*N, time+1, 1);
                    }
                }
            }
        }
        return;
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);

        s = sc.nextInt();
        N = sc.nextInt();
        K = sc.nextInt();
        r1 = sc.nextInt();
        r2 = sc.nextInt();
        c1 = sc.nextInt();
        c2 = sc.nextInt();
        ans = new int[r2-r1+1][c2-c1+1];

        makeFractal(0, 0, 1, 0);


        for (int i = 0; i < r2-r1+1; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }
    
        
        bw.flush();
        bw.close();

    }
}
