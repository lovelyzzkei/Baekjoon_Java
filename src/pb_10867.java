import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_10867 {

    static int n;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Integer[] arrInt = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        List<Integer> list = Arrays.asList(arrInt);
        HashSet<Integer> set = new HashSet<Integer>(list);
        arrInt = set.toArray(new Integer[0]);
        Arrays.sort(arrInt);
       
        for (int n : arrInt) {
            bw.write(n + " ");
        }

        bw.flush();
        bw.close();

    }
}
