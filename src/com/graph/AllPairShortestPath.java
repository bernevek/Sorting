package com.graph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

public class AllPairShortestPath {
    Map<String, Result> output = new LinkedHashMap<>();
    private int infinity = 999999;
    private int v;
    private int graph[][];
    private String path[][];

    public void floydWarshall() {
        readFile();

        for (int k = 1; k < v; k++) {
            for (int i = 1; i < v; i++) {
                if (graph[i][k] != infinity) {
                    for (int j = 1; j < v; j++) {
                        String key = "d[" + i + "," + j + "]=";
                        if (graph[i][k] + graph[k][j] < graph[i][j]) {
                            graph[i][j] = graph[i][k] + graph[k][j];
                            path[i][j] = path[i][k].substring(0, 1) + "-" + path[k][j];
                            output.get(key).setPathLength(graph[i][j]);
                            output.get(key).setPath(path[i][j]);
                        }
                    }
                }
            }
        }
        printResult();
    }

    private void printResult() {
        for (Map.Entry<String, Result> entry : output.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
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
            graph[Integer.valueOf(strings[0])][Integer.valueOf(strings[1])] = Integer.valueOf(strings[2]);
        }

        path = new String[v][v];
        for (int i = 1; i < v; i++) {
            for (int j = 1; j < v; j++) {
                String key = "d[" + i + "," + j + "]=";
                if (i == j) {
                    path[i][j] = "";
                } else {
                    path[i][j] = i + "-" + j;
                    output.put(key, new Result(graph[i][j], path[i][j]));
                }
            }
        }
    }

    private class Result {
        private int pathLength = 0;
        private String path = "";

        public Result(int pathLength, String path) {
            this.pathLength = pathLength;
            this.path = path;
        }

        public void setPathLength(int pathLength) {
            this.pathLength = pathLength;
        }

        public void setPath(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return pathLength + "\tPATH: " + path;
        }
    }
}
