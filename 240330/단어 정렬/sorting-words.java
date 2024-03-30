import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
            ss[i] = in.readLine();
        }

        Arrays.sort(ss);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(ss[i]).append("\n");
        }
        
        System.out.print(sb.toString());
    }
}