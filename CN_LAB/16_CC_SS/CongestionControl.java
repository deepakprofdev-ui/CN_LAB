public class CongestionControl {
    public static void main(String[] args) {
        int cwnd = 1, ssthresh = 16, maxRound = 20;
        System.out.printf("%-8s %-8s %-10s%n", "Round", "CWND", "Phase");
        System.out.println("-------------------------------");

        for (int round = 1; round <= maxRound; round++) {
            String phase;
            if (cwnd < ssthresh) {
                phase = "Slow Start";
                cwnd *= 2;
            } else {
                phase = "Cong. Avoid";
                cwnd++;
            }
            System.out.printf("%-8d %-8d %-10s%n", round, cwnd, phase);

            // Simulate packet loss at cwnd = 24
            if (cwnd >= 24) {
                System.out.println("*** Packet Loss! ssthresh = " + cwnd/2 + ", cwnd = 1 ***");
                ssthresh = cwnd / 2;
                cwnd = 1;
            }
        }
    }
}
