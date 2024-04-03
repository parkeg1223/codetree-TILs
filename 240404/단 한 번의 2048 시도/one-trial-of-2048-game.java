import java.io.*;
import java.util.*;

public class Main {
    static int[][] field = new int[4][4];

    public static void slide(char dir) {
        int[][] temp = new int[4][4];
        if (dir == 'L') {
            for (int i = 0; i < 4; i++) {
                int prev = 0, tempIdx = 0;
                boolean isCombinable = true;
                for (int j = 0; j < 4; j++) {
                    if (field[i][j] != 0) {
                        if (prev == field[i][j] && isCombinable) {
                            prev = temp[i][tempIdx-1] *= 2;
                            isCombinable = false;
                        } else {
                            temp[i][tempIdx++] = field[i][j];
                            prev = field[i][j];
                            isCombinable = true;
                        }
                    }
                }
            }
        } else if (dir == 'R') {
            for (int i = 0; i < 4; i++) {
                int prev = 0, tempIdx = 3;
                boolean isCombinable = true;
                for (int j = 3; j >= 0; j--) {
                    if (field[i][j] != 0) {
                        if (prev == field[i][j] && isCombinable) {
                            prev = temp[i][tempIdx+1] *= 2;
                            isCombinable = false;
                        } else {
                            temp[i][tempIdx--] = field[i][j];
                            prev = field[i][j];
                            isCombinable = true;
                        }
                    }
                }
            }
        } else if (dir == 'U') {
            for (int i = 0; i < 4; i++) {
                int prev = 0, tempIdx = 0;
                boolean isCombinable = true;
                for (int j = 0; j < 4; j++) {
                    if (field[j][i] != 0) {
                        if (prev == field[j][i] && isCombinable) {
                            prev = temp[tempIdx-1][i] *= 2;
                            isCombinable = false;
                        } else {
                            temp[tempIdx++][i] = field[j][i];
                            prev = field[j][i];
                            isCombinable = true;
                        }
                    }
                }
            }
        } else if (dir == 'D') {
            for (int i = 0; i < 4; i++) {
                int prev = 0, tempIdx = 3;
                boolean isCombinable = true;
                for (int j = 3; j >= 0; j--) {
                    if (field[j][i] != 0) {
                        if (prev == field[j][i] && isCombinable) {
                            prev = temp[tempIdx+1][i] *= 2;
                            isCombinable = false;
                        } else {
                            temp[tempIdx--][i] = field[j][i];
                            prev = field[j][i];
                            isCombinable = true;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            field[i] = Arrays.copyOf(temp[i], 4);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < 4; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        slide(in.readLine().charAt(0));

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(field[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}