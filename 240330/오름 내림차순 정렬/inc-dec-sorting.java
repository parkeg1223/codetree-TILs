import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        for (int i = n-1; i >= 0; i--) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}