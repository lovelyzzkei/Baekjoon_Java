import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_11503 {

    static int n;
    static int[] A;
    static int[] cache;
    static ArrayList<Integer> elements = new ArrayList<>();
    static ArrayList<String> cacheElements = new ArrayList<String>();

    static int lis(int start) {
        int ret = cache[start];
        if (ret != -1) return ret;
        
        cache[start] = 1;
        elements.add(A[start]);
        for (int i = start + 1; i < n; i++) {
            if (A[start] < A[i]) {
                cache[start] = Math.max(cache[start], 1 + lis(i));
            }
        }

        String tmp = "";
        for (int i = 0; i < elements.size(); i++) {
            tmp += elements.get(i);
            tmp += " ";
        }

        cacheElements.add(tmp);
        elements.remove(elements.size()-1);
        return cache[start];
    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        cache = new int[n];
        

        Arrays.fill(cache, -1);
        A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int ret = 0;
        String arr = "";
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, lis(i));
            if (cacheElements.get(i).length() >= arr.length()) {
                arr = cacheElements.get(i);
            }
        }



        bw.write(String.format("%d\n", ret));
        bw.write(arr);
        bw.flush();
        bw.close();

    }
}
