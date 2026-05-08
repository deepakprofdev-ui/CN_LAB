import java.net.*;
import java.io.*;

public class HTTPWebClient {
    public static void main(String[] args) throws Exception {
        String host = "www.example.com";
        Socket s = new Socket(host, 80);

        // Send HTTP GET request
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println("GET / HTTP/1.0");
        out.println("Host: " + host);
        out.println();

        // Read response and save to file
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        FileWriter fw = new FileWriter("downloaded.html");
        String line;
        boolean body = false;
        while ((line = in.readLine()) != null) {
            if (line.isEmpty()) body = true;
            if (body) fw.write(line + "\n");
            System.out.println(line);
        }
        fw.close();
        s.close();
        System.out.println("\nPage saved as downloaded.html");
    }
}
