import java.io.*;
import java.util.*;

class Number implements Comparable<Number> {
    int idx, val;

    public Number() {}
    public Number(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Number n) {
        if (this.val == n.val) {
            return this.idx - n.idx;
        }
        return this.val - n.val;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine().trim());

        Number[] numbers = new Number[N];
        int[] newPos = new int[N];
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = new Number(i, Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(numbers);
        for (int i = 0; i < N; i++) {
            newPos[numbers[i].idx] = i+1;
        }

        for (int i = 0; i < N; i++) {
            sb.append(newPos[i]).append(' ');
        }
        System.out.println(sb.toString());
    }
}