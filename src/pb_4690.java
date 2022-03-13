import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class pb_4690 {
    
    public static void main(String args[]) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 4중 for문을 사용하여 Brute-Force로 해결
        for (int a = 1; a <= 100; a++) {
            for (int b = 2; b <= 100; b++) {
                for (int c = b; c <= 100; c++) {
                    for (int d = c; d <= 100; d++) {
                        double triple = Math.pow(b, 3) + Math.pow(c, 3) + Math.pow(d, 3);
                        if (Math.pow(a, 3) == triple) {
                            bw.write(String.format("Cube = %s, Triple = (%s,%s,%s)\n", a, b, c, d));
                        }
                    }
                }
            }
        }


        bw.flush();
        bw.close();
    }
}
