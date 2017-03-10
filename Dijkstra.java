import java.util.*;
import java.io.*;

public class Dijkstra {
  public static class Pair {
    int j, weight;
    public Pair(int j, int weight) {
      this.j = j;
      this.weight = weight;
    }
  }
  public static int[] dijkstra(int start, int N, ArrayList<Pair>[] adjList) {
    int[] dist = new int[N + 1];
    Arrays.fill(dist, (int)(1e9));
    PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
      @Override
      public int compare(Pair a, Pair b) {
        return new Integer(a.weight).compareTo(b.weight);
      }
    });
    pq.offer(new Pair(start, 0));
    dist[start] = 0;
    while (!pq.isEmpty()) {
      Pair p = pq.poll();
      int curr_dist = p.weight;
      int w = p.j;
      for (Pair e : adjList[w]) {
        int u = e.j;
        if (dist[u] > dist[w] + e.weight) {
          dist[u] = dist[w] + e.weight;
          pq.offer(new Pair(u, dist[u]));
        }
      }
    }
    return dist;
  }

  public static void main(String... args) {
    // Graph from the wikipedia page for Dijkstras
    // https://upload.wikimedia.org/wikipedia/commons/5/57/Dtra_Animation.gif
    int N = 6;
    ArrayList<Pair>[] adjList = new ArrayList[N + 1];
    for (int i = 0; i <= N; i++)
      adjList[i] = new ArrayList<Pair>();
    adjList[1].add(new Pair(3, 9));
    adjList[1].add(new Pair(2, 7));
    adjList[1].add(new Pair(6, 14));
    adjList[2].add(new Pair(3, 10));
    adjList[2].add(new Pair(4, 15));
    adjList[2].add(new Pair(1, 7));
    adjList[3].add(new Pair(6, 2));
    adjList[3].add(new Pair(2, 10));
    adjList[3].add(new Pair(1, 9));
    adjList[3].add(new Pair(4, 11));
    adjList[4].add(new Pair(5, 6));
    adjList[4].add(new Pair(3, 11));
    adjList[4].add(new Pair(2, 15));
    adjList[5].add(new Pair(6, 9));
    adjList[5].add(new Pair(4, 6));
    adjList[6].add(new Pair(5, 9));
    adjList[6].add(new Pair(3, 2));
    adjList[6].add(new Pair(1, 14));
    int[] dist = dijkstra(1, N, adjList);
    System.out.println(Arrays.toString(dist));
  }
}

