package pagerank;

import findshortestpath.Vertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static findshortestpath.Vertex.getValuesAsString;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (!(line = br.readLine()).trim().equals("")) {
            String[] splittedLine = line.split("\\t");

            String vertexNum = splittedLine[0];
            String pageRank = splittedLine[1];
            String[] neighbors = splittedLine[2].substring(splittedLine[2].indexOf("{") + 1, splittedLine[2].indexOf("}")).split(",");

            if (neighbors[0].equals("")) {
                neighbors = new String[]{};
            }

            Vertex vertex = new Vertex(vertexNum);
            vertex.setNeighbors(Arrays.asList(neighbors));
            vertex.setPageRank(pageRank);
            double probability = round(Double.parseDouble(pageRank) / vertex.getNeighbors().size(), 3);
            vertex.setProbability(probability);

            System.out.println(vertex.getStringLabel() + "\t" +
                    vertex.getPageRank() + "\t" +
                    "{" + getValuesAsString(vertex.getNeighbors()) + "}");

            for (String s : vertex.getNeighbors()) {
                System.out.println(s + "\t" +
                        vertex.getProbability() + "\t" + "{}");
            }
        }

    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private static boolean isNode(Vertex v) {
        return v.getNeighbors().size() > 0;
    }
}
