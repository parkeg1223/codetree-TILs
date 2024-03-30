import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        List<Integer> nList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            nList.add(Integer.parseInt(st.nextToken()));
            if (i % 2 == 0) {
                Collections.sort(nList);
                sb.append(nList.get(i/2)).append(" ");
            }
        }

        System.out.println(sb.toString());
    }
}