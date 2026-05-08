import java.net.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        ds.setSoTimeout(3000);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter domain name: ");
        String domain = sc.nextLine().trim();

        byte[] buf = domain.getBytes();
        ds.send(new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 9090));

        byte[] rbuf = new byte[1024];
        DatagramPacket rp = new DatagramPacket(rbuf, rbuf.length);
        ds.receive(rp);
        String result = new String(rp.getData(), 0, rp.getLength());

        if (result.startsWith("ERROR"))
            System.out.println("DNS Error: " + result);
        else
            System.out.println("IP Address: " + result);

        ds.close();
    }
}
