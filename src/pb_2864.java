import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2864 {



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        int max_a = Integer.parseInt(input[0].replaceAll("5", "6"));
        int max_b = Integer.parseInt(input[1].replaceAll("5", "6"));

        int min_a = Integer.parseInt(input[0].replaceAll("6", "5"));
        int min_b = Integer.parseInt(input[1].replaceAll("6", "5"));
        

        bw.write((min_a + min_b) + " " + (max_a + max_b));
        bw.flush();
        bw.close();

    }
}
