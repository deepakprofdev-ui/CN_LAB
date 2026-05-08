import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ARPClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter IP to resolve: ");
        String ip = sc.nextLine();

        Socket s      = new Socket("localhost", 4040);
        PrintWriter out   = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        out.println(ip);
        String mac = in.readLine();

        if (mac.equals("NOT_FOUND"))
            System.out.println("ARP Failed: No MAC found for " + ip);
        else
            System.out.println("ARP Reply: " + ip + " -> " + mac);

        s.close();
    }
}
