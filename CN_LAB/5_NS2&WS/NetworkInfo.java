import java.net.*;
import java.util.*;

public class NetworkInfo {
    public static void main(String[] args) throws Exception {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();

            System.out.println("Interface: " + ni.getName());
            System.out.println("Display Name: " + ni.getDisplayName());

            Enumeration<InetAddress> addrs = ni.getInetAddresses();

            while (addrs.hasMoreElements()) {
                InetAddress addr = addrs.nextElement();
                System.out.println("IP Address: " + addr.getHostAddress());
            }

            System.out.println();
        }
    }
}