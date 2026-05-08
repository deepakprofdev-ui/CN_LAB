import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(6000);
        System.out.println("UDP Server started...");
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        ds.receive(dp);
        String msg = new String(dp.getData(), 0, dp.getLength());
        System.out.println("Received from Client: " + msg);
        // Echo back
        byte[] reply = msg.getBytes();
        DatagramPacket rp = new DatagramPacket(reply, reply.length, dp.getAddress(), dp.getPort());
        ds.send(rp);
        System.out.println("Echoed back: " + msg);
        ds.close();
    }
}
