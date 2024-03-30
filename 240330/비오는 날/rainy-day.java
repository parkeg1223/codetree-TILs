import java.io.*;
import java.util.*;

class Forecast {
    String date, day, weather;

    public Forecast() {}
    public Forecast(String date, String day, String weather) {
        this.date = date;
        this.day = day;
        this.weather = weather;
    }

    public void getInfo() {
        System.out.println(this.date + " " + this.day + " " + this.weather);
    }    
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        List<Forecast> fList = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());            
            Forecast f = new Forecast(st.nextToken(), st.nextToken(), st.nextToken());
            if (f.weather.equals("Rain")) fList.add(f);
        }

        int lIdx = 0;
        for (int i = 1; i < fList.size(); i++) {
            if (fList.get(i).date.compareTo(fList.get(lIdx).date) < 0) lIdx = i;
        }

        fList.get(lIdx).getInfo();
    }
}