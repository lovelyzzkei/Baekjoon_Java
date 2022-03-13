import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_17829 {

    static int N;
    static int[][] cnn;

    static double baseLog(double x, double base) {
        return Math.log(x) / Math.log(base);
    }
         

    static void pooling(int y, int x, int n) {
        if (n == 2) {
            int[] arr = new int[4];
            arr[0] = cnn[y][x];
            arr[1] = cnn[y][x+1];
            arr[2] = cnn[y+1][x];
            arr[3] = cnn[y+1][x+1];

            Arrays.sort(arr);
            cnn[y/2][x/2] = arr[2];
            return;
        }

        pooling(y, x, n/2);
        pooling(y, x+n/2, n/2);
        pooling(y+n/2, x, n/2);
        pooling(y+n/2, x+n/2, n/2);
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        cnn = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                cnn[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 1; i <= (int) baseLog(N, 2); i++) {
            pooling(0, 0, N/i);
        }
        bw.write(String.format("%d", cnn[0][0]));

        
        bw.flush();
        bw.close();

    }
}
