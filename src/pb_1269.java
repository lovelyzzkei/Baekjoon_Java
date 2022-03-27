import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1269 {

    static int numA, numB;
    static HashSet<Integer> A, Acopy, B;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        numA = Integer.parseInt(input[0]);
        numB = Integer.parseInt(input[1]);

        int[] aArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        A = new HashSet<>();
        Acopy = new HashSet<>();
        for (Integer i : aArr) {
            A.add(i);
            Acopy.add(i);
        }

        int[] bArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        B = new HashSet<>();
        for (Integer i : bArr) {
            B.add(i);
        }

        A.removeAll(B);
        B.removeAll(Acopy);

        bw.write((A.size() + B.size()) + "");
        bw.flush();
        bw.close();

    }
}
