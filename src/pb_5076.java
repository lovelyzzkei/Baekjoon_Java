import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_5076 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String input = br.readLine();
            if (input.equals("#")) break;

            Deque<String> dq = new ArrayDeque<>();

            int idx = 0;
            int bracketStart = 0;
            int bracketEnd = 0;
            boolean isSatisfied = true;
            while (idx < input.length()) {

                // <를 찾으면 >도 찾아서 그 안의 태그를 구함
                if (input.charAt(idx) == '<') {

                    bracketStart =  idx;
                    bracketEnd = idx;

                    while (true) {
                        if (input.charAt(bracketEnd) != '>') bracketEnd++;
                        else break;
                    }

                    String content = input.substring(bracketStart+1, bracketEnd);
                    String tag = content.split(" ")[0];

                    idx = bracketEnd + 1;

                    // 찾은 태그가 start와 end를 모두 만족하는 하나의 태그가 아닐 경우에만 스택에 삽입
                    if (content.charAt(content.length()-1) == '/') {
                        continue;
                    }

                    // 찾은 태그가 closing tag이면 스택의 맨 위에 태그와 비교. 같은 태그여야만 조건 만족.
                    if (tag.charAt(0) == '/') {
                        if (dq.size() == 0 || !dq.removeLast().equals(tag.substring(1))) {
                            isSatisfied = false;
                            break;
                        }
                    } else {
                        dq.add(tag);
                    }

                    
                } else {
                    idx++;
                }

                // System.out.println(dq.toString());
            }

            if (isSatisfied && dq.size() == 0) {
                bw.write("legal\n");
            } else {
                bw.write("illegal\n");
            }
        }

        bw.flush();
        bw.close();

    }
}
