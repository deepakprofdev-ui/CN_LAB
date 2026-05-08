import java.util.Arrays;

public class RoutingProtocols {
    static final int INF = 9999, N = 5;

    // RIP - Bellman-Ford (Distance Vector)
    static void RIP(int[][] c) {
        System.out.println("=== RIP - Distance Vector (Bellman-Ford) ===");
        int[][] cost = new int[N][N];
        for (int i = 0; i < N; i++) cost[i] = c[i].clone();

        for (int k = 0; k < N - 1; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    for (int m = 0; m < N; m++)
                        if (cost[i][m] != INF && cost[m][j] != INF &&
                            cost[i][m] + cost[m][j] < cost[i][j])
                            cost[i][j] = cost[i][m] + cost[m][j];

        System.out.println("Routing Table from Node 0:");
        for (int j = 0; j < N; j++)
            System.out.println("  0 -> " + j + " : " + (cost[0][j] == INF ? "INF" : cost[0][j]));
    }

    // OSPF - Dijkstra (Link State)
    static void OSPF(int[][] graph) {
        System.out.println("\n=== OSPF - Link State (Dijkstra) ===");
        int[] dist = new int[N];
        boolean[] vis = new boolean[N];
        Arrays.fill(dist, INF); dist[0] = 0;

        for (int i = 0; i < N - 1; i++) {
            int u = -1;
            for (int v = 0; v < N; v++)
                if (!vis[v] && (u == -1 || dist[v] < dist[u])) u = v;
            vis[u] = true;
            for (int v = 0; v < N; v++)
                if (graph[u][v] != INF && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        System.out.println("Shortest paths from Node 0:");
        for (int i = 0; i < N; i++)
            System.out.println("  0 -> " + i + " : " + dist[i]);
    }

    public static void main(String[] args) {
        int[][] cost = {
            {0,   2,   INF, 1,   INF},
            {2,   0,   3,   INF, INF},
            {INF, 3,   0,   4,   1  },
            {1,   INF, 4,   0,   5  },
            {INF, INF, 1,   5,   0  }
        };
        RIP(cost);
        OSPF(cost);
        System.out.println("\nComparison:");
        System.out.println("RIP  : Simple, hop-count metric, slow convergence, max 15 hops");
        System.out.println("OSPF : Uses Dijkstra, bandwidth metric, faster convergence, scalable");
    }
}
