import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_13325 {

    static int k, dist;
    static int[] d;

    static int dfs(int curr) {
        if (curr >= Math.pow(2, k+1)-1) return 0;

        int left = d[curr] + dfs(2*curr+1);
        int right = d[curr] + dfs(2*curr+2);

        if (left != right) {
            dist += (int) Math.abs(left-right);
        }
        
        return Math.max(left, right);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        k = Integer.parseInt(br.readLine());
        d = Stream.of(("0 "+ br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        dist = 0;
        int distToLeaf = dfs(0);

        int sum = dist;
        for (int num : d) {
            sum += num;
        }
        System.out.println(sum);
        
        bw.flush();
        bw.close();

    }
}
