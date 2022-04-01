import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1547 {

    static int m;
    static int[] cup;

    static void swap(int a, int b) {
        int tmp = cup[a];
        cup[a] = cup[b];
        cup[b] = tmp;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        m = Integer.parseInt(br.readLine());
        cup = new int[4];
        cup[1] = 1; cup[2] = 2; cup[3] = 3;
        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            swap(a, b);
        }

        int ret = -1;
        for (int i = 1; i <= 3; i++) {
            if (cup[i] == 1) ret = i;
        }
        
        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
