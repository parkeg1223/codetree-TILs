import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        boolean[] isExist = new boolean[101];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            isExist[Integer.parseInt(st.nextToken())] = true;
        }

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            if (!isExist[Integer.parseInt(st.nextToken())]) {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }
}