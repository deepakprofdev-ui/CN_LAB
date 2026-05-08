import java.net.*;
import java.util.*;

public class DNSServer {
    public static void main(String[] args) throws Exception {
        Map<String, String> dns = new HashMap<>();
        dns.put("www.google.com",   "142.250.190.46");
        dns.put("www.yahoo.com",    "98.137.11.163");
        dns.put("www.example.com",  "93.184.216.34");
        dns.put("www.amazon.com",   "176.32.103.205");
        dns.put("www.facebook.com", "157.240.241.35");

        DatagramSocket ds = new DatagramSocket(9090);
        System.out.println("DNS Server running on port 9090...");

        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            ds.receive(dp);
            String domain = new String(dp.getData(), 0, dp.getLength()).trim();
            System.out.println("Query for: " + domain);

            String ip = dns.getOrDefault(domain, "ERROR: Domain not resolved");
            byte[] resp = ip.getBytes();
            ds.send(new DatagramPacket(resp, resp.length, dp.getAddress(), dp.getPort()));
        }
    }
}
