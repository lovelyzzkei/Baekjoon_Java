import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1517 {

    static long arr[];
    
    static long bubbleSort(int start, int end) {
        if (start == end) {
            return 0;
        }

        int mid = (start + end) / 2;
        long left = bubbleSort(start, mid);
        long right = bubbleSort(mid+1, end);
        long swap = 0;

        // 양쪽 정렬 된 것을 합침
        int pointer_left = start, pointer_right = mid+1;
        long sortedArr[] = new long[end-start+1];
        int idx = 0;

        while (idx <= end-start && pointer_right < end + 1 && pointer_left < mid + 1) {

            long front_left = arr[pointer_left];
            long front_right = arr[pointer_right];

            if (front_left > front_right) {
                sortedArr[idx] = front_right;
                swap += (mid - pointer_left + 1);
                pointer_right++;
            } else {
                sortedArr[idx] = front_left;
                pointer_left++;
            }
            idx++;

        }
        
        if (pointer_left < mid + 1) {
            for (int i = pointer_left; i < mid + 1; i++) {
                sortedArr[idx++] = arr[i];
            }
        } 

        if (pointer_right < end + 1) {
            for (int i = pointer_right; i < end + 1; i++) {
                sortedArr[idx++] = arr[i];
            }
        }

        idx = 0;
        for (int i = start; i <= end; i++) {
            arr[i] = sortedArr[idx++];
        }
        
        // System.out.println("SortedArr: " + Arrays.toString(sortedArr));
        // System.out.println(Arrays.toString(arr));
        // System.out.println(String.format("%d %d %d", swap, left, right));

        return swap + left + right;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       
        int N = Integer.parseInt(br.readLine());
        arr = Stream.of(br.readLine().split(" ")).mapToLong(Integer::parseInt).toArray();

        bw.write(Long.toString(bubbleSort(0, arr.length-1)));
       
        bw.flush();
        bw.close();

    }
}
