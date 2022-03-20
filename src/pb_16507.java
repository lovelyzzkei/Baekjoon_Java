import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_16507 {

    static int r, c, q;
    static int[][] brightness, pSum;
    static int r1, c1, r2, c2;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        q = Integer.parseInt(input[2]);

        brightness = new int[r][c];
        for (int[] arr : brightness) {
            int[] tmp = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(tmp, 0, arr, 0, c);
        }

        pSum = new int[r+1][c+1];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                pSum[i+1][j+1] = pSum[i][j+1] + pSum[i+1][j] - pSum[i][j] + brightness[i][j];
            }
        }

        for (int t = 0; t < q; t++) {
            input = br.readLine().split(" ");
            r1 = Integer.parseInt(input[0]);
            c1 = Integer.parseInt(input[1]);
            r2 = Integer.parseInt(input[2]);
            c2 = Integer.parseInt(input[3]);

            int sum = pSum[r2][c2] - pSum[r1-1][c2] - pSum[r2][c1-1] + pSum[r1-1][c1-1];
            int numOfPicture = (r2-r1+1) * (c2-c1+1);
            bw.write(sum / numOfPicture + "\n");
        }

       
        bw.flush();
        bw.close();

    }
}
