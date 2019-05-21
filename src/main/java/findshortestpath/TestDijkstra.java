package findshortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class TestDijkstra {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String splittedLine[] = br.readLine().split("\\s");
        int vertexCount = _int(splittedLine[0]);
        int edgeCount = _int(splittedLine[1]);

        Graph graph = new Graph(vertexCount);

        for (int i = 1; i <= vertexCount; i++) {
            graph.addVertex(String.valueOf(i));
        }

        for (int i = 0; i < edgeCount; i++) {
            splittedLine = br.readLine().split("\\s");
            graph.addEdge(_int(splittedLine[0]) - 1, _int(splittedLine[1]) - 1, _int(splittedLine[2]));
        }

        splittedLine = br.readLine().split("\\s");

        Dijkstra dijkstra = new Dijkstra();
        String fromNode = splittedLine[0];
        String node = splittedLine[1];

        LinkedHashMap<String, Integer> shortestPath = dijkstra.getShortestPathFromNodeToNode(graph, fromNode, node);

        System.out.println(shortestPath.get(node) == null ? -1 : shortestPath.get(node));
    }

    private static int _int(String s) {
        return Integer.parseInt(s);
    }
}