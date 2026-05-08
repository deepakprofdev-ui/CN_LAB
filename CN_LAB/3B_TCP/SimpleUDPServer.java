import java.net.*;

public class SimpleUDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(8080);
        System.out.println("Simple UDP Server ready...");
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        ds.receive(dp);
        System.out.println("Client says: " + new String(dp.getData(), 0, dp.getLength()));
        String response = "Hello from Server!";
        byte[] rbuf = response.getBytes();
        ds.send(new DatagramPacket(rbuf, rbuf.length, dp.getAddress(), dp.getPort()));
        ds.close();
    }
}
