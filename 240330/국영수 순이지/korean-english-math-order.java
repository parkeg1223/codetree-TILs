import java.io.*;
import java.util.*;

class Student implements Comparable<Student> {
    String name;
    int korean, english, math;

    public Student() {}
    public Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    public void getInfo() {
        System.out.println(this.name + " " + this.korean + " " + this.english + " " + this.math);
    }

    @Override
    public int compareTo(Student s) {
        if (this.korean == s.korean) {
            if (this.english == s.english) {
                return s.math - this.math;
            }
            return s.english - this.english;
        }
        return s.korean - this.korean;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        Student[] students = new Student[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            students[i] = new Student(
                st.nextToken(),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            );
        }

        Arrays.sort(students);
        for (int i = 0; i < n; i++) {
            students[i].getInfo();
        }
    }
}