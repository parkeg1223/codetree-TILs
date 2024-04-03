import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(in.readLine().trim());
        int[] numbers = new int[n];


        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(in.readLine().trim());
        }
        
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken());
            int[] temp = new int[n];
            Arrays.fill(numbers, s, e, 0);

            int tempIdx = 0;
            for (int j = 0; j < n; j++) {
                if (numbers[j] != 0) temp[tempIdx++] = numbers[j];
            }
            
            n = tempIdx;
            for (int j = 0; j < n; j++) {
                numbers[j] = temp[j];
            }
        }

        sb.append(n).append('\n');
        for (int i = 0; i < n; i++) {
            sb.append(numbers[i]).append('\n');
        }
        System.out.print(sb.toString());
    }
}