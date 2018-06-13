package com.graph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

public class BreadthFirstSearch {
    private int infinity = 999999;
    private int v;
    private int graph[][];

    public void BFS(int start, int finish) {
        readFile();
        boolean visited[] = new boolean[v];

        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<String> result = new LinkedList<>();

        visited[start] = true;
        queue.add(start);
        result.add(String.valueOf(start));

        while (queue.size() != 0) {
            if (queue.isEmpty()) {
                break;
            }
            int s = queue.removeFirst();

            if (s == finish) {
                System.out.println("Shortest path from " + start + " to " + finish + " - " + result.removeFirst());
                return;
            }

            String previousPath = result.removeFirst();
            for (int i = 1; i < v; i++) {
                int n = graph[s][i];
                if (n == 1) {
                    if (!visited[i]) {
                        visited[i] = true;
                        queue.add(i);
                        result.add(previousPath + "-" + i);
                    }
                }
            }
        }
        System.out.println("path not found");
    }


    private void readFile() {
        File file = new File("floyd.txt");
        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String stringGraph = new String(fileContent);
        String[] stringArrayGraph = stringGraph.trim().split("\n");
        v = Integer.valueOf(stringArrayGraph[0].trim().split(" ")[0]);
        graph = new int[v][v];
        for (int i = 1; i < v; i++) {
            for (int j = 1; j < v; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = infinity;
                }
            }
        }
        String[] strings;
        for (int i = 1; i < stringArrayGraph.length; i++) {
            strings = stringArrayGraph[i].trim().split(" ");
            graph[Integer.valueOf(strings[0])][Integer.valueOf(strings[1])] = 1;
        }
    }
}
