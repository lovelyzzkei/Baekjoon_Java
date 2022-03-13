import java.io.*;
import java.util.*;

public class pb_1802 {

    static String paper;

    static int foldPaper(int s, int e) {
        if (s == e) {
            return paper.charAt(s) - '0';
        }
        int mid = (s + e) / 2;
        int left = foldPaper(s, mid-1);
        int right = foldPaper(mid+1, e);
        if ((left ^ right) == 1) {
            return paper.charAt(mid) - '0';
        } else {
            return -1;
        }
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            paper = br.readLine();
            int mid = foldPaper(0, paper.length()-1);
            if (mid == 0 || mid == 1) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }

        System.out.println(0b100 & 0b011);
       
        bw.flush();
        bw.close();

    }
}
