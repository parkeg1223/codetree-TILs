import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    int x, y, idx;

    public Point() {}
    public Point(int x, int y, int idx) {
        this.x = x;
        this.y = y;
        this.idx = idx;
    }

    @Override
    public int compareTo(Point p) {
        int mDist = Math.abs(this.x) + Math.abs(this.y) - Math.abs(p.x) - Math.abs(p.y);
        if (mDist == 0) return this.idx - p.idx;
        else return mDist;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        Point[] points = new Point[N];
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            points[i] = new Point(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                i+1
            );
        }

        Arrays.sort(points);
        for (int i = 0; i < N; i++) {
            sb.append(points[i].idx).append('\n');
        }
        System.out.print(sb.toString());
    }
}