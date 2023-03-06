import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2313 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        LinkedList<Integer[]> ptrs = new LinkedList<>();

        int sum = 0;
        for (int i = 0; i < n; i++) {
            int l = Integer.parseInt(br.readLine());
            int[] values = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] maxEndingAt = new int[l];

            int start = 0;
            int end = 0;
            int num = 1;
            maxEndingAt[0] = values[0];
            int maxValue = -10001;

            for (int j = 1; j < l; j++) {
                maxEndingAt[j] = Math.max(maxEndingAt[j-1] + values[j], values[j]);
                if (maxEndingAt[j-1] + values[j] < values[j]) {
                    start = j;
                    end = j;
                    num = 1;
                }

                System.out.println(Arrays.toString(maxEndingAt) + " "+ maxValue);

                if (maxEndingAt[j] >= maxValue) {
                    // System.out.println(j + " " + end);
                    if ((maxEndingAt[j] == maxValue && j < end) ||
                        maxEndingAt[j] > maxValue) {
                        end = j;
                        num = end - start;
                    }
                    maxValue = maxEndingAt[j];                    
                }
                
            }
            ptrs.add(new Integer[]{start+1, end+1});
            sum += maxValue;
        }

        bw.write(sum + "\n");
        for (Integer[] arr : ptrs) {
            bw.write(arr[0] + " " + arr[1] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
