import java.net.*;

public class SimpleUDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        String msg = "Hello Server!";
        byte[] buf = msg.getBytes();
        ds.send(new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 8080));
        byte[] rbuf = new byte[1024];
        DatagramPacket rp = new DatagramPacket(rbuf, rbuf.length);
        ds.receive(rp);
        System.out.println("Server says: " + new String(rp.getData(), 0, rp.getLength()));
        ds.close();
    }
}
