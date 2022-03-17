import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1212 {    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String num = br.readLine();
        for (int i = 0; i < num.length(); i++) {
            String numToBinary = Integer.toBinaryString(num.charAt(i)-'0');
            if (i != 0 && numToBinary.length() < 3) {
                for (int j = 0; j < 3-numToBinary.length(); j++) {
                    bw.write("0");
                }
            }
            bw.write(numToBinary);
        }

        bw.flush();
        bw.close();
    }
}

