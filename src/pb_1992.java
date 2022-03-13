import java.io.*;
import java.util.*;


public class pb_1992 {

    static int[][] video;

    static boolean allBlack(int y, int x, int n) {
        boolean isBlack = true;
        for (int i = y; i < y+n; i++) {
            for (int j = x; j < x+n; j++) {
                if (video[i][j] != 1) {
                    isBlack = false;
                }
            }
        }

        return isBlack;
    }

    static boolean allWhite(int y, int x, int n) {
        boolean isWhite = true;
        for (int i = y; i < y+n; i++) {
            for (int j = x; j < x+n; j++) {
                if (video[i][j] != 0) {
                    isWhite = false;
                }
            }
        }

        return isWhite;
    }

    static String quadTree(int y, int x, int n) {
        if (allBlack(y, x, n)) {
            return "1";
        }

        if (allWhite(y, x, n)) {
            return "0";
        }

        String upperLeft = quadTree(y, x, n/2);
        String upperRight = quadTree(y, x+n/2, n/2);
        String lowerLeft = quadTree(y+n/2, x, n/2);
        String lowerRight = quadTree(y+n/2, x+n/2, n/2);
        return "("+ upperLeft + upperRight + lowerLeft + lowerRight +")";
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 1. 입력받기
        int n = Integer.parseInt(br.readLine());
        video = new int[n][n];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                video[i][j] = Integer.parseInt(input.substring(j, j+1));
            }
        }

        // 2. 압축 시작
        System.out.println(quadTree(0, 0, n));

        bw.flush();
        bw.close();

    }
}
