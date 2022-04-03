import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2010 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int num = 1;
        for (int i = 0; i < n; i++) {
            num--;
            num += Integer.parseInt(br.readLine());
        }
        bw.write(num+"");
        bw.flush();
        bw.close();

    }
}
