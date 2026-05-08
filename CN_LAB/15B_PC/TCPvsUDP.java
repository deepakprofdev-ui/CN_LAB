public class TCPvsUDP {
    public static void main(String[] args) {
        System.out.println("TCP vs UDP Performance Comparison:");
        System.out.println("------------------------------------");
        System.out.printf("%-20s %-15s %-15s%n", "Feature", "TCP", "UDP");
        System.out.printf("%-20s %-15s %-15s%n", "Connection", "Yes", "No");
        System.out.printf("%-20s %-15s %-15s%n", "Reliability", "Yes", "No");
        System.out.printf("%-20s %-15s %-15s%n", "Ordering", "Yes", "No");
        System.out.printf("%-20s %-15s %-15s%n", "Speed", "Slower", "Faster");
        System.out.printf("%-20s %-15s %-15s%n", "Overhead", "High", "Low");
        System.out.printf("%-20s %-15s %-15s%n", "Use Case", "HTTP/FTP", "DNS/Video");
    }
}
