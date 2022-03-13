import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2810 {
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();
        input = input.replaceAll("LL", "A");
        int flag = input.indexOf("A");
        int ret = (flag == -1) ? input.length() : input.length() + 1;
        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
