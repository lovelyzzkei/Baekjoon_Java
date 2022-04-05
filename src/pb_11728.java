import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_11728 {

    static int n, m;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        int[] a = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] newArr = new int[n+m];
        int ptrA = 0;
        int ptrB = 0;
        int idx = 0;

        while (ptrA < n && ptrB < m) {
            if (a[ptrA] < b[ptrB]) {
                newArr[idx++] = a[ptrA++];
            } else {
                newArr[idx++] = b[ptrB++];
            }
        }

        for (int i = ptrA; i < n; i++) {
            newArr[idx++] = a[i];
        }

        for (int i = ptrB; i < m; i++) {
            newArr[idx++] = b[i];
        }

        for (int n : newArr) {
            bw.write(n + " ");
        }
        bw.flush();
        bw.close();

    }
}
