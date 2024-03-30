import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String T = st.nextToken();

        List<String> sList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = in.readLine();
            if (s.startsWith(T)) sList.add(s);
        }

        Collections.sort(sList);

        System.out.println(sList.get(k-1));
    }
}