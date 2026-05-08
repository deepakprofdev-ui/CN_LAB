import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 7777);
        System.out.println("Connected to Chat Server!");

        BufferedReader in  = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out    = new PrintWriter(s.getOutputStream(), true);
        Scanner sc         = new Scanner(System.in);

        // Thread to receive messages
        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null)
                    System.out.println("Server: " + msg);
            } catch (Exception e) { System.out.println("Server disconnected."); }
        }).start();

        // Send messages
        System.out.println("Type messages (type 'bye' to quit):");
        while (true) {
            String msg = sc.nextLine();
            out.println(msg);
            if (msg.equalsIgnoreCase("bye")) break;
        }
        s.close();
    }
}
