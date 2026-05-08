import java.net.*;
import java.io.*;

// Run TCPPerfServer first, then TCPPerfClient

public class TCPPerfServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("TCP Server ready...");
        Socket s = ss.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        int count = 0;
        long start = System.currentTimeMillis();
        while (in.readLine() != null) count++;
        long end = System.currentTimeMillis();
        System.out.println("Received " + count + " messages in " + (end - start) + " ms");
        s.close(); ss.close();
    }
}
