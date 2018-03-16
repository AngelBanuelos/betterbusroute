/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author angel_banuelos
 */
public class Kruskal {

    public class Edge implements Comparable<Edge> {

        public int vertex1, vertex2;
        public double weight;

        public Edge(int vertex1, int vertex2, double weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight < o.weight) {
                return -1; // If it is lower than return -1
            }
            if (this.weight > o.weight) {
                return 1; // If it is greter than return 1
            }
            return 0; // if are equals return 0
        }
    }

    private int findLeader(int[] parents, int x) {
        while (parents[x] != x) {
            x = parents[x];
        }
        return x;
    }

    private boolean join(int[] parents, int[] rank, int i, int j) {
        int leaderI = findLeader(parents, i);
        int leaderJ = findLeader(parents, j);
        if (leaderI == leaderJ) {
            return false;
        }

        int rankLeaderI = rank[leaderI];
        int rankLeaderJ = rank[leaderJ];

        if (rankLeaderI > rankLeaderJ || (rankLeaderI == rankLeaderJ && leaderI > leaderJ)) {
            parents[leaderJ] = leaderI;
            rank[leaderI] += rank[leaderJ];
        } else {
            parents[leaderI] = leaderJ;
            rank[leaderJ] += rank[leaderI];
        }
        return true;
    }

    public List<Edge> kruskal(double[][] graph, int start) {
        PriorityQueue<Edge> queue = new PriorityQueue();
        List<Edge> arm = new ArrayList<Edge>(graph.length - 1);
        for (int i = 0; i < graph.length; i++) {
            for (int j = i + 1; j < graph[i].length; j++) {
                if (graph[i][j] != GraphConstants.INF) {
                    Edge e = new Edge(i, j, graph[i][j]);
                    queue.offer(e);
                }
            }
        }
        int[] parents = new int[graph.length];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        int[] rank = new int[graph.length];
        for (int i = 0; i < rank.length; i++) {
            rank[i] = 1;
        }

        while (arm.size() < graph.length - 1) {
            Edge minEdge = queue.poll();
            if (join(parents, rank, minEdge.vertex1, minEdge.vertex2)) {
                arm.add(minEdge);
            }
        }
        return arm;
    }

    public static void main(String[] args) {
        double[][] graph = {{GraphConstants.INF, GraphConstants.INF, GraphConstants.INF, 4, GraphConstants.INF, 2},
        {GraphConstants.INF, GraphConstants.INF, 5, GraphConstants.INF, 3, GraphConstants.INF},
        {GraphConstants.INF, 5, GraphConstants.INF, 1, 3, 2},
        {4, GraphConstants.INF, 1, GraphConstants.INF, 4, 3},
        {GraphConstants.INF, 3, 3, 4, GraphConstants.INF, GraphConstants.INF},
        {2, GraphConstants.INF, 2, 3, GraphConstants.INF, GraphConstants.INF}
        };
 
        Kruskal k = new Kruskal();
        List<Edge> arm = k.kruskal(graph, 0);
        for (Edge edge : arm) {
            System.out.println(edge.vertex1 + " " + edge.vertex2 + " " + edge.weight);
        }
    }
}
