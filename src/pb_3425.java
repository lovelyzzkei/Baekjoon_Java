import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_3425 { 

    static final int INF = 1_000_000_000;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {

            // QUIT이 나오면 프로그램 종료. 그렇지 않으면 프로그램 명령어를 입력 받음
            Deque<String> program = new ArrayDeque<>();

            String input = br.readLine();
            if (input.equals("QUIT")) break;
            program.add(input);

            // 첫줄에 END가 아닐 경우에만 추가적으로 명령어를 더 입력받음
            if (!input.equals("END")) {
                while (true) {
                    input = br.readLine();
                    if (input.equals("END")) break;
                    program.add(input);
                }
            }

            Object[] programArr = program.toArray();
            // System.out.println(Arrays.toString(programArr));

            // 입력 받는 프로그램의 수행 횟수만큼 위 명령어들로 이루어진 프로그램을 수행
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                Deque<Long> stack = new ArrayDeque<>();
                stack.add(Long.parseLong(br.readLine()));  // 초기값 스택에 삽입

                // 해당 스택에 대해 프로그램 수행
                int programSize = programArr.length;
                boolean isError = false;

                for (int j = 0; j < programSize; j++) {

                    if (isError) {
                        break;
                    }


                    String command = (String) programArr[j];
                    Long top = (long) 0;
                    Long second = (long) 0;
                    
                    switch (command.split(" ")[0]) {
                        case "NUM":
                            stack.add(Long.parseLong(command.split(" ")[1]));
                            break;

                        case "POP":
                            top = stack.pollLast();
                            if (top == null) {
                                isError = true;
                                break;
                            }
                            break;

                        case "INV":
                            top = stack.pollLast();
                            if (top == null) {
                                isError = true;
                                break;
                            }
                            stack.add(-top);
                            break;

                        case "DUP":
                            top = stack.peekLast();
                            if (top == null) {
                                isError = true;
                                break;
                            }
                            stack.add(top);
                            break;

                        case "SWP":
                            top = stack.pollLast();
                            second = stack.pollLast();
                            if (top == null || second == null) {
                                isError = true;
                                break;
                            }
                            stack.add(top);
                            stack.add(second);
                            break;

                        case "ADD":
                            top = stack.pollLast();
                            second = stack.pollLast();
                            if (top == null || second == null) {
                                isError = true;
                                break;
                            }
                            stack.add(top + second);
                            break;
                        
                        case "SUB":
                            top = stack.pollLast();
                            second = stack.pollLast();
                            if (top == null || second == null) {
                                isError = true;
                                break;
                            }
                            stack.add(second - top);
                            break;

                        case "MUL":
                            top = stack.pollLast();
                            second = stack.pollLast();
                            if (top == null || second == null) {
                                isError = true;
                                break;
                            }
                            stack.add(top*second);
                            break;

                        case "DIV":
                            top = stack.pollLast();
                            second = stack.pollLast();
                            if (top == null || second == null || top == 0) {
                                isError = true;
                                break;
                            }
                            stack.add(second / top);
                            break;

                        case "MOD":
                            top = stack.pollLast();
                            second = stack.pollLast();
                            if (top == null || second == null || top == 0) {
                                isError = true;
                                break;
                            }
                            stack.add(second % top);
                            break;
                    }
                    

                    // System.out.println(stack.toString());

                }

                if (isError || (!stack.isEmpty() && Math.abs(stack.peekLast()) > INF)) {
                    isError = true;
                }

                if (isError || stack.size() != 1) {
                    bw.write("ERROR\n");
                } else {
                    bw.write(stack.getLast()+"\n");
                }
            }

            // 마지막 공백 받기
            input = br.readLine();
            bw.write("\n");
        }
        

        bw.flush();
        bw.close();

    }
}
