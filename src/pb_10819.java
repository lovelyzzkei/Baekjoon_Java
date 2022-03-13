import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.stream.Stream;

public class pb_10819 {

    static int ans = 0;

    static void pick(int n, ArrayList<Integer> picked, ArrayList<Integer> nums) {
        if (picked.size() == n) {

            int sum = 0;
            for (int i = 0; i < n-1; i++){
                sum += (Math.abs(picked.get(i)- picked.get(i+1)));
            }

            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            picked.add(nums.get(i));
            ArrayList<Integer> nextNums = new ArrayList<>();
            for (int j = 0; j < nums.size(); j++) {
                if (j != i) {
                    nextNums.add(nums.get(j));
                }
            }
            pick(n, picked, nextNums);
            picked.remove(picked.size()-1);
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        ArrayList<Integer> A = new ArrayList<>();
        for (int s: input) {
            A.add(s);
        }

        ArrayList<Integer> picked = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pick(n, picked, A);
        }
        bw.write(String.format("%d", ans));
        bw.flush();
        bw.close();
    }
}
