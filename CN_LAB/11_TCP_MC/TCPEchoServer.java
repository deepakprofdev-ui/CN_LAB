import java.net.*;
import java.io.*;

public class TCPEchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5050);
        System.out.println("Echo Server running on port 5050...");
        while (true) {
            Socket s = ss.accept();
            System.out.println("Client connected: " + s.getInetAddress());
            // Handle each client in a new thread
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    PrintWriter out   = new PrintWriter(s.getOutputStream(), true);
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println("Received: " + msg);
                        out.println("Echo: " + msg);
                    }
                    s.close();
                } catch (Exception e) { e.printStackTrace(); }
            }).start();
        }
    }
}
