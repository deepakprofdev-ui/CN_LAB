import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("Chat Server started. Waiting for client...");
        Socket s = ss.accept();
        System.out.println("Client connected!");

        BufferedReader in  = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out    = new PrintWriter(s.getOutputStream(), true);
        Scanner sc         = new Scanner(System.in);

        // Thread to receive messages
        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null)
                    System.out.println("Client: " + msg);
            } catch (Exception e) { System.out.println("Client disconnected."); }
        }).start();

        // Send messages
        System.out.println("Type messages (type 'bye' to quit):");
        while (true) {
            String msg = sc.nextLine();
            out.println(msg);
            if (msg.equalsIgnoreCase("bye")) break;
        }
        s.close(); ss.close();
    }
}
