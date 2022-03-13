import java.io.*;
import java.util.*;

public class pb_5904 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       
        int[] num = {1, 2, 3};
        int[] newnum = new int[6];

        System.arraycopy(num, 0, newnum, 0, num.length);
        System.out.println(Arrays.toString(newnum));
       
        bw.flush();
        bw.close();

    }
}
