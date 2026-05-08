import java.net.*;
import java.io.*;

public class JavaServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5500);
        System.out.println("Java Server waiting for C client...");
        Socket s = ss.accept();
        System.out.println("C Client connected!");
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out   = new PrintWriter(s.getOutputStream(), true);
        String msg = in.readLine();
        System.out.println("C Client says: " + msg);
        out.println("Hello from Java Server!");
        s.close(); ss.close();
    }
}

