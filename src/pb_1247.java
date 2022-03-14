import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

public class pb_1247 {    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int t = 0; t < 3; t++) {
            int n = Integer.parseInt(br.readLine());
            BigDecimal sum = BigDecimal.ZERO;
            for (int i = 0; i < n; i++) {
                sum = sum.add(BigDecimal.valueOf(Long.parseLong(br.readLine())));
            }


            if (sum.compareTo(BigDecimal.ZERO) > 0) bw.write("+\n");
            else if (sum.compareTo(BigDecimal.ZERO) == 0) bw.write("0\n");
            else  bw.write("-\n");
        }


        bw.flush();
        bw.close();

    }
}

