import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1543 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String document = br.readLine();
        String reg = br.readLine();

        int ret = 0;
        while (document.indexOf(reg) != -1) {
            ret++;
            document = document.substring(document.indexOf(reg) + reg.length());
        }

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
