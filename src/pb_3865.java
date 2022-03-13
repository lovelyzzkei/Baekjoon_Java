import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_3865 {  

    static int n;
    static HashMap<String, String[]> academy;
    static HashMap<String, Boolean> visited;

    static int dfs(String root) {
        visited.put(root, true);
        int leaf = 0;
        if (academy.get(root) == null) {
            leaf = 1;
        } else {
            for (String next : academy.get(root)) {
                if (visited.get(next) == null) {
                    leaf += dfs(next);
                }
            }
        }
        
        return leaf;
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {

            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            // 학회의 수 만큼 HashMap을 초기화
            // 입력을 받아 ':'로 쪼개서 key : string, value : string[] (맨 뒤에 점 제거) 형태로 저장
            String root = "";
            academy = new HashMap<String, String[]>();
            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split(":");
                if (i == 0) root = input[0];
                academy.put(input[0], input[1].substring(0, input[1].length()-1).split(","));
            }

            visited = new HashMap<String, Boolean>();
            int ret = dfs(root);
            
            bw.write(+ ret+"\n");
        }

        bw.flush();
        bw.close();

    }
}
