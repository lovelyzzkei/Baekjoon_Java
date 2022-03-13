import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class pb_10974 {
    
    static int n;
    static int[] picked;

    static void permutation(int n, int now){
        if (now == n) {
            for (int i: picked) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= n; i++) {

            if (!Arrays.asList(picked).contains(i)){
                picked[now] = i;
                permutation(n, now+1);
                picked[now] = 0;
            }
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        picked = new int[n];

        permutation(n, 0);
    
    }
}
