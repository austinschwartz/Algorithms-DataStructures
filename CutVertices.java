import java.io.*;
import java.util.*;

public class CutVertices {
  public static int cutCount;
  public static boolean[] visited, cut;
  public static int[] low, depth, parent;
  public static ArrayList<Integer>[] adjList;

  // Tarjans Algorithm from
  // https://en.wikipedia.org/wiki/Biconnected_component
  public static void dfs(int i, int d) {
    visited[i] = true;
    depth[i] = d;
    low[i] = d;
    int childCount = 0;
    boolean isCut = false;
    for (int u : adjList[i]) {
      if (!visited[u]) {
        parent[u] = i;
        dfs(u, d + 1);
        childCount += 1;
        if (low[u] >= depth[i]) {
          isCut = true;
        }
        low[i] = Math.min(low[i], low[u]);
      } else if (u != parent[i]) {
        low[i] = Math.min(low[i], depth[u]);
      }
    }
    if ((parent[i] != -1 && isCut) || 
        (parent[i] == -1 && childCount > 1)) {
      cut[i] = true;
      System.out.println("Vertex " + i + " is a cut vertex");
      cutCount += 1;
    }
  }

  public static void main(String[] args) {
    // Graph from the wiki page:
    // https://en.wikipedia.org/wiki/Biconnected_component
    int N = 14;
    adjList = new ArrayList[N];
    for (int i = 0; i < N; i++)
      adjList[i] = new ArrayList<Integer>();
    adjList[0].add(1);
    adjList[0].add(2);
    adjList[1].add(0);
    adjList[1].add(3);
    adjList[2].add(0);
    adjList[2].add(3);
    adjList[3].add(1);
    adjList[3].add(2);
    adjList[3].add(4);
    adjList[4].add(3);
    adjList[4].add(5);
    adjList[5].add(4);
    adjList[5].add(6);
    adjList[6].add(5);
    adjList[6].add(7);
    adjList[6].add(8);
    adjList[6].add(9);
    adjList[7].add(6);
    adjList[7].add(10);
    adjList[7].add(11);
    adjList[8].add(6);
    adjList[9].add(6);
    adjList[9].add(12);
    adjList[9].add(13);
    adjList[10].add(7);
    adjList[10].add(11);
    adjList[11].add(10);
    adjList[11].add(7);
    adjList[11].add(13);
    adjList[12].add(9);
    adjList[13].add(11);
    adjList[13].add(9);
    visited = new boolean[N];
    cut = new boolean[N];
    low = new int[N];
    parent = new int[N];
    Arrays.fill(parent, -1);
    depth = new int[N];

    cutCount = 0;
    for (int i = 0; i < N; i++)
      if (adjList[i].size() > 0)
        dfs(i, 0);
    System.out.println("# of cut vertices: " + cutCount);
  }
}
