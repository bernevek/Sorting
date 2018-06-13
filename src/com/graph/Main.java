package com.graph;


public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Floyd-Warshall");
        AllPairShortestPath allPairShortestPath = new AllPairShortestPath();
        allPairShortestPath.floydWarshall();
        System.out.println("\nBreadth-first search");
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        breadthFirstSearch.BFS(2, 3);
    }
}
