import java.io.*;
import java.util.*;

public class pb_4779 {
    
    static int n;
    static char[] cantor;

    static void makeCantor(int start, int end, int step) {
        if (step == n) {
            return;
        }

        int interval = (end-start) / 3;
        for (int i = start + interval; i < start + interval*2; i++) {
            cantor[i] = ' '; 
        }
        // System.out.println(String.valueOf(cantor));

        makeCantor(start, start+interval, step+1);
        makeCantor(start+interval*2, end, step+1);
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 1. 입력 받기
        while (true) {
            String input = br.readLine();
            if (input == null) {
                break;
            }

            n = Integer.parseInt(input);
            cantor = new char[(int) Math.pow(3, n)];
            Arrays.fill(cantor, '-');
            makeCantor(0, cantor.length, 0);
            bw.write(String.valueOf(cantor));
            bw.write('\n');
            bw.flush();
        }


        bw.flush();
        bw.close();

    }
}
