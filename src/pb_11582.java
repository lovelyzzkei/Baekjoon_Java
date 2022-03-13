import java.io.*;
import java.util.*;
import java.util.stream.Stream;



public class pb_11582 {

    static int N;
    static int[] chicken;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        

    // [start, end) 구간
    static void mergeSort(int start, int end, int k) throws IOException {
        if (end - start == 1) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(start, mid, k);   // 왼쪽 정렬
        mergeSort(mid, end, k);     // 오른쪽 정렬

        // 합치기
        int[] sortedArr = new int[end-start];
        int p_l = start, p_r = mid, idx = 0;
        while (p_l < mid && p_r < end) {
            if (chicken[p_l] < chicken[p_r]) {
                sortedArr[idx++] = chicken[p_l++];
            } else {
                sortedArr[idx++] = chicken[p_r++];
            }
        }

        // 남은거 그대로 합치기
        if (p_l < mid) {
            for (int i = p_l; i < mid; i++) {
                sortedArr[idx++] = chicken[i];
            }
        }

        if (p_r < end) {
            for (int i = p_r; i < end; i++) {
                sortedArr[idx++] = chicken[i];
            }
        }

        System.arraycopy(sortedArr, 0, chicken, start, end-start);
       
        if (end - start == N / k) {
            StringBuilder ret = new StringBuilder();
            for (int i = start; i < end; i++) {
                ret.append(Integer.toString(chicken[i]));
                ret.append(" ");
            }
            bw.write(ret.toString());
            return;
        }
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 입력 받기 
        N = Integer.parseInt(br.readLine());
        chicken = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(br.readLine());

        mergeSort(0, N, k);
        bw.write('\n');
        bw.flush();
        bw.close();

    }
}
