import java.io.*;
import java.util.*;

public class pb_2448 {

    static int N;
    static char star[][];

    static void drawStar(int y, int x, int height) {
        if (height == 3) {
            star[y][x] = '*';
            star[y+1][x-1] = star[y+1][x+1] = '*';
            for (int i = -2; i <= 2; i++) {
                star[y+2][x+i] = '*';
            }
            return;
        }

        drawStar(y, x, height/2);
        drawStar(y + height/2, x - height/2, height/2);
        drawStar(y + height/2, x + height/2, height/2);
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        star = new char[3500][6500];

        drawStar(0, N-1, N);
        for (int i = 0; i < N; i++) {
            StringBuffer line = new StringBuffer();
            for (int j = 0; j < N*2; j++) {
                line.append((star[i][j] == '*') ? '*' : " ");
            }
            line.append('\n');
            bw.write(line.toString());
        }
       
        bw.flush();
        bw.close();

    }
}
