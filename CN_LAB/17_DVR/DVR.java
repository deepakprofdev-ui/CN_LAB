public class DVR {
    static final int INF = 9999;

    public static void main(String[] args) {
        int n = 4;
        // Cost matrix
        int[][] cost = {
            {0,   1,   INF, 4  },
            {1,   0,   2,   INF},
            {INF, 2,   0,   3  },
            {4,   INF, 3,   0  }
        };
        int[][] next = new int[n][n];

        // Initialize next hop
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                next[i][j] = (cost[i][j] != INF && i != j) ? j : -1;

        // Bellman-Ford
        for (int k = 0; k < n - 1; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    for (int m = 0; m < n; m++)
                        if (cost[i][m] != INF && cost[m][j] != INF &&
                            cost[i][m] + cost[m][j] < cost[i][j]) {
                            cost[i][j] = cost[i][m] + cost[m][j];
                            next[i][j] = next[i][m];
                        }

        System.out.println("=== Distance Vector Routing Table ===");
        System.out.print("    ");
        for (int i = 0; i < n; i++) System.out.print("N" + i + "\t");
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print("N" + i + ": ");
            for (int j = 0; j < n; j++)
                System.out.print((cost[i][j] == INF ? "INF" : cost[i][j]) + "\t");
            System.out.println();
        }

        // Print paths
        System.out.println("\n=== Shortest Paths from N0 ===");
        for (int j = 1; j < n; j++) {
            System.out.print("N0 -> N" + j + ": N0");
            int cur = 0;
            while (cur != j) { cur = next[cur][j]; System.out.print(" -> N" + cur); }
            System.out.println(" (Cost: " + cost[0][j] + ")");
        }
    }
}
