import java.io.*;
import java.util.*;

public class test {
	static BufferedWriter bw;
	static BufferedReader br;

	static int fibo(int n) {
		return n + fibo(n-1);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int ans = fibo(10);

		bw.write(ans + "");
		bw.flush();
		bw.close();
    }
}