import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message to send: ");
        String msg = sc.nextLine();
        byte[] buf = msg.getBytes();
        InetAddress addr = InetAddress.getByName("localhost");
        ds.send(new DatagramPacket(buf, buf.length, addr, 6000));
        byte[] rbuf = new byte[1024];
        DatagramPacket rp = new DatagramPacket(rbuf, rbuf.length);
        ds.receive(rp);
        String received = new String(rp.getData(), 0, rp.getLength());
        System.out.println("Echo from Server: " + received);
        System.out.println("Match: " + msg.equals(received));
        ds.close();
    }
}
