import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_12846 {
    
    static int n;
    static int[] wage;
    
    static int calWage(int start, int end){
        if (start == end) return wage[start];

        // 양 쪽에서의 최대 임금 반환
        int mid = (start + end) / 2;
        int left = calWage(start, mid);
        int right = calWage(mid+1, end);
        int ret = Math.max(left, right);
        
        // 중간을 걸친 최대
        int lo = mid, hi = mid;
        int middleWage = wage[mid];

        while (start < lo || hi < end) {
            if (hi < end && (lo == start || wage[lo-1] < wage[hi+1])) {
                middleWage = Math.min(middleWage, wage[++hi]);
            } else {
                middleWage = Math.min(middleWage, wage[--lo]);
            }
            ret = Math.max(ret, middleWage * (hi - lo + 1));
        }

        return ret;
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 1. 입력 받기
        n = Integer.parseInt(br.readLine());
        wage = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int maxWage = calWage(0, n-1);

        bw.write(String.format("%d", maxWage));
        bw.flush();
        bw.close();

    }
}
