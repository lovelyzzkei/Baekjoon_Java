import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_16194 {

    static int n;
    static int[] card, cache;
    
    // num개를 갖기 위해 지불해야하는 금액의 최솟값
    static int pay(int num) {
        if (num == 0) return 0;
        if (cache[num] != -1) return cache[num];
        cache[num] = card[num-1];
        for (int i = 1; i <= num; i++) {
            cache[num] = Math.min(cache[num], pay(num-i) + pay(i));
        }
        return cache[num];
    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        card = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cache = new int[n+1];
        Arrays.fill(cache, -1);

        int ret = pay(n);
        
        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
