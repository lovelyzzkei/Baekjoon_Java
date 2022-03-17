import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_14947 {    

    static int n, m;
    static int destY, destX;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;

    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < m);
    }

    static boolean isBoxArrived(int y, int x, int base) {
        // 밑면이 1x1인 경우 도착 판별
        if (base == 1) {
            return (y == destY && x == destX);
        } 
        
        // 밑면이 1x3인 경우 도착 판별
        else {
            if (inRange(y+2, x) && (y+2 == destY && x == destX)) return true;
            if (inRange(y, x+2) && (y == destY && x+2 == destX)) return true;
        }

        return false;
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        // "y, x, 밑면" 으로 저장
        // 밑면 = 1 : 1 x 1, 3 : 1 x 3
        Deque<String> q = new ArrayDeque<>();
        visited = new boolean[n][m];

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] inputArr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = inputArr[j] - '0';

                // 시작 위치 저장
                if (inputArr[j] == '2') {
                    q.add(i + " " + j + " " + 1);
                    visited[i][j] = true;
                }

                // 도착 위치 저장
                if (inputArr[j] == '3') {
                    destY = i; destX = j;
                    map[i][j] = 1;
                }
            }
        }

        // 상자 옮기기 시작
        int cnt = 0;
        boolean isArrived = false;

        while (!q.isEmpty()) {

            int qSize = q.size();
            for (int t = 0; t < qSize; t++) {
                String[] curr = q.pop().split(" ");
                int y = Integer.parseInt(curr[0]);
                int x = Integer.parseInt(curr[1]);
                int base = Integer.parseInt(curr[2]);

                // 밑면이 1x3일때 목표 지점에 도달한 경우
                // 현재 y,x가 도달 or y+3,x y,x+3이 도달한 상태
                if (isBoxArrived(y, x, base)) {
                    isArrived = true;
                    break;
                }

                // 다음 위치 판별
                for (int i = 0; i < 4; i++) {

                    // 현재 밑면이 1x1이면 다음 밑면이 1x3이 되므로
                    // 다음 밑면과 다다다음 밑면이 모두 땅이거나, 다다음 밑면이 땅인 경우에만 이동이 가능하다
                    if (base == 1) {
                        int firstBaseY = y + dy[i]; int firstBaseX = x + dx[i];
                        int secondBaseY = y + 2*dy[i]; int secondBaseX = x + 2*dx[i];
                        int thirdBaseY = y + 3*dy[i]; int thirdBaseX = x + 3*dx[i];

                        if (inRange(firstBaseY, firstBaseX) && inRange(thirdBaseY, thirdBaseX)) {

                            // 세 칸 모두 범위에 있을 때 양쪽이 모두 1인 경우
                            if (map[firstBaseY][firstBaseX] == 1 && map[thirdBaseY][thirdBaseX] == 1) {
                                if (!visited[firstBaseY][firstBaseX]) {
                                    visited[firstBaseY][firstBaseX] = true;
                                    q.add(firstBaseY + " " + firstBaseX + " " + 3);
                                }
                            }

                            // 가운데 칸만 1인 경우
                            else if (map[firstBaseY][firstBaseX] == 0 && map[thirdBaseY][thirdBaseX] == 0 && map[secondBaseY][secondBaseX] == 1) {
                                if (!visited[secondBaseY][secondBaseX]) {
                                    visited[secondBaseY][secondBaseX] = true;
                                    q.add(secondBaseY + " " + secondBaseX + " " + 1);
                                }
                            }
                        }
                        
                    }

                    // 밑면이 1x3인 경우에는 옆으로도 굴릴수가 있음
                    else {
                        int ny = y + base*dy[i];
                        int nx = x + base*dx[i];

                        if (inRange(ny, nx) && map[ny][nx] == 1) {
                            if (!visited[ny][nx]) {
                                visited[ny][nx] = true;
                                q.add(ny + " " + nx + " " + 1);
                            }
                        }
                    }
                }

                System.out.println(q.toString());


            }

            if (isArrived) break;
            cnt++;
        }

        bw.write((isArrived ? cnt : -2) + "");
        bw.flush();
        bw.close();

    }
}

