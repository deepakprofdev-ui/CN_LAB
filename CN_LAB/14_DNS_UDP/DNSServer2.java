import java.net.*;
import java.util.*;

public class DNSServer2 {
    public static void main(String[] args) throws Exception {
        Map<String, String> dns = new HashMap<>();
        dns.put("www.google.com",    "142.250.190.46");
        dns.put("www.github.com",    "140.82.113.4");
        dns.put("www.openai.com",    "104.18.7.192");
        dns.put("www.wikipedia.org", "208.80.154.224");

        DatagramSocket ds = new DatagramSocket(9191);
        System.out.println("DNS Server running on port 9191...");

        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            ds.receive(dp);
            String domain = new String(dp.getData(), 0, dp.getLength()).trim();
            System.out.println("Lookup: " + domain);
            String result = dns.containsKey(domain)
                ? "Resolved: " + domain + " -> " + dns.get(domain)
                : "ERROR: Domain '" + domain + "' not found in DNS records";
            byte[] resp = result.getBytes();
            ds.send(new DatagramPacket(resp, resp.length, dp.getAddress(), dp.getPort()));
        }
    }
}
