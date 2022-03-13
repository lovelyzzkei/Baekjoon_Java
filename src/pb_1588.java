import java.io.*;
import java.util.*;

public class pb_1588 {

    static int N, Left, Right;
    static int numOfOne = 0, numOfTwo = 0, numOfThree = 0;
    static int[][][] cache = new int[3][21][3];

    static void makeSequence(int num, int x, int time) {
        if (time == N) {
            if (Left <= x && x <= Right){
                switch (num) {
                    case 1:
                        numOfOne += 1;
                        break;
                    case 2:
                        numOfTwo += 1;
                        break;
                    case 3:
                        numOfThree += 1;
                        break;
                }
            }
            return;
        }
       
        if (time == N-1) {
            if (3*x > Right || 3*x+2 < Left) {
                return;
            } else if (Left <= 3*x && 3*x+2 <= Right) {
                switch (num) {
                    case 1:
                        numOfOne += 1;
                        numOfTwo += 1;
                        numOfThree += 1;
                        break;
                    case 2:
                        numOfOne += 2;
                        numOfTwo += 1;
                        break;
                    case 3:
                        numOfTwo += 2;
                        numOfThree += 1;
                        break;
                }
                return;
            }
        }

        if (num == 1) {
            makeSequence(1, 3*x, time+1);
            makeSequence(3, 3*x+1, time+1);
            makeSequence(2, 3*x+2, time+1);

        } else if (num == 2) {
            makeSequence(2, 3*x, time+1);
            makeSequence(1, 3*x+1, time+1);
            makeSequence(1, 3*x+2, time+1);
        } else {
            makeSequence(2, 3*x, time+1);
            makeSequence(3, 3*x+1, time+1);
            makeSequence(2, 3*x+2, time+1);
        }

        return;
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int start = Integer.parseInt(br.readLine());
        Left = Integer.parseInt(br.readLine());
        Right = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());


        int a = 1, b = 0, c = 0;
        int na, nb, nc;
        for (int n = 1; n <= 20; n++) {
            na = a + b*2;
            nb = a + b + 2*c;
            nc = a + c;
            a = na; b = nb; c = nc;
            cache[0][n][0] = a; cache[0][n][1] = b; cache[0][n][2] = c;
        }

        a = 0; b = 1; c = 0;
        for (int n = 1; n <= 20; n++) {
            na = a + b*2;
            nb = a + b + 2*c;
            nc = a + c;
            a = na; b = nb; c = nc;
            cache[1][n][0] = a; cache[1][n][1] = b; cache[1][n][2] = c;
        }
        a = 0; b = 0; c = 1;
        for (int n = 1; n <= 20; n++) {
            na = a + b*2;
            nb = a + b + 2*c;
            nc = a + c;
            a = na; b = nb; c = nc;
            cache[2][n][0] = a; cache[2][n][1] = b; cache[2][n][2] = c;
        }

        makeSequence(start, 0, 0);
        
        bw.write(String.format("%d %d %d", numOfOne, numOfTwo, numOfThree));
        bw.flush();
        bw.close();

    }
}
