import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPEchoClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5050);
        PrintWriter out   = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        Scanner sc        = new Scanner(System.in);

        System.out.println("Connected to Echo Server. Type messages:");
        while (true) {
            System.out.print("You: ");
            String msg = sc.nextLine();
            out.println(msg);
            System.out.println(in.readLine());
            if (msg.equalsIgnoreCase("exit")) break;
        }
        s.close();
    }
}
