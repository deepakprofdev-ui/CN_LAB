import java.util.*;

public class ARP {
    // ARP Table: IP -> MAC
    static Map<String, String> arpTable = new HashMap<>();

    static {
        arpTable.put("192.168.1.1", "AA:BB:CC:DD:EE:01");
        arpTable.put("192.168.1.2", "AA:BB:CC:DD:EE:02");
        arpTable.put("192.168.1.3", "AA:BB:CC:DD:EE:03");
        arpTable.put("192.168.1.4", "AA:BB:CC:DD:EE:04");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== ARP Protocol Simulation ===");
        System.out.println("ARP Table (IP -> MAC):");
        arpTable.forEach((ip, mac) -> System.out.println("  " + ip + "  ->  " + mac));

        System.out.print("\nEnter IP Address to find MAC: ");
        String ip = sc.nextLine().trim();

        System.out.println("Broadcasting ARP Request: Who has " + ip + "?");

        if (arpTable.containsKey(ip)) {
            System.out.println("ARP Reply: " + ip + " is at " + arpTable.get(ip));
        } else {
            System.out.println("ARP Failed: No host found for IP " + ip);
        }
    }
}
