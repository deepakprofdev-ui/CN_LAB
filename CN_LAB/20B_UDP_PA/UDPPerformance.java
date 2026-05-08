import java.net.*;

public class UDPPerformance {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        InetAddress addr = InetAddress.getByName("localhost");
        int packets = 500;
        byte[] buf = "TestPacket".getBytes();

        long start = System.currentTimeMillis();
        for (int i = 0; i < packets; i++)
            ds.send(new DatagramPacket(buf, buf.length, addr, 6000));
        long end = System.currentTimeMillis();

        long duration = end - start;
        System.out.println("UDP Performance Test:");
        System.out.println("Packets Sent : " + packets);
        System.out.println("Time Taken   : " + duration + " ms");
        System.out.println("Throughput   : " + (packets * 10.0 / duration * 1000) + " bytes/sec");
        ds.close();
    }
}
