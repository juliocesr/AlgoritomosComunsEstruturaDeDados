package org.program;

import java.util.*;

public class Dijkstra {
    static class Graph {
        private final int vertices;
        private final List<List<Node>> adj;

        public Graph(int vertices) {
            this.vertices = vertices;
            adj = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adj.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination, int weight) {
            adj.get(source).add(new Node(destination, weight));
        }

        public void dijkstra(int start) {
            int[] distances = new int[vertices];
            boolean[] visited = new boolean[vertices];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[start] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
            pq.add(new Node(start, 0));

            while (!pq.isEmpty()) {
                int current = pq.poll().vertex;
                if (visited[current]) continue;
                visited[current] = true;

                for (Node neighbor : adj.get(current)) {
                    if (!visited[neighbor.vertex]) {
                        int newDist = distances[current] + neighbor.weight;
                        if (newDist < distances[neighbor.vertex]) {
                            distances[neighbor.vertex] = newDist;
                            pq.add(new Node(neighbor.vertex, newDist));
                        }
                    }
                }
            }

            for (int i = 0; i < distances.length; i++) {
                System.out.println("Distância do vértice " + start + " ao vértice " + i + " é " + distances[i]);
            }
        }
    }

    static class Node {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
