import java.net.*;
import java.io.*;

public class TCPPerfClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) out.println("Message " + i);
        s.close();
        System.out.println("Sent 1000 messages in " + (System.currentTimeMillis() - start) + " ms");
    }
}
