import java.net.*;
import java.util.*;
import java.io.*;

public class ARPServer {
    static Map<String, String> arpTable = new HashMap<>();
    static {
        arpTable.put("192.168.1.10", "00:1A:2B:3C:4D:50");
        arpTable.put("192.168.1.20", "00:1A:2B:3C:4D:60");
        arpTable.put("192.168.1.30", "00:1A:2B:3C:4D:70");
    }

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(4040);
        System.out.println("ARP Server running...");
        while (true) {
            Socket s = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out   = new PrintWriter(s.getOutputStream(), true);
            String ip = in.readLine();
            System.out.println("ARP Request for IP: " + ip);
            String mac = arpTable.getOrDefault(ip, "NOT_FOUND");
            out.println(mac);
            s.close();
        }
    }
}
