import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1049 {

    static int n, m;
    static int[] pack, ea, cache;
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        pack = new int[m];
        ea = new int[m];
        
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            pack[i] = Integer.parseInt(input[0]);
            ea[i] = Integer.parseInt(input[1]);
        }

        Arrays.sort(pack);
        Arrays.sort(ea);

        int ret = 0;
        while (n > 0) {
            if (n > 6) {
                ret += (pack[0] <= ea[0] * 6) ? pack[0] : ea[0] * 6;
                n -= 6;
            } else {
                ret += (pack[0] <= ea[0] * n) ? pack[0] : ea[0] * n;
                n = 0;
            }
        }





        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
