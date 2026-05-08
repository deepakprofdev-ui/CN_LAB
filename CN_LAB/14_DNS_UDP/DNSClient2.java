import java.net.*;
import java.util.Scanner;

public class DNSClient2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        ds.setSoTimeout(5000);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter domain to resolve: ");
        String domain = sc.nextLine();
        byte[] buf = domain.getBytes();
        ds.send(new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 9191));
        byte[] rbuf = new byte[1024];
        DatagramPacket rp = new DatagramPacket(rbuf, rbuf.length);
        ds.receive(rp);
        System.out.println(new String(rp.getData(), 0, rp.getLength()));
        ds.close();
    }
}

