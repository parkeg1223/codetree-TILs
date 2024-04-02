import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        List<Integer> nList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                nList.add(Integer.parseInt(st.nextToken()));
            }
        }

        int nMove = t % (3*n);
        Collections.rotate(nList, nMove);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(nList.get(i*n+j)).append(' ');
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}