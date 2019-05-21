package findshortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static findshortestpath.Vertex.getValuesAsString;

public class MapRedSimple {

    public static void main(String[] args) throws IOException {
//        //Mapper
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//            String splittedLine[] = line.split("\\t");
//            String key = splittedLine[0];
//            String[] neighbors = splittedLine[2].substring(splittedLine[2].indexOf("{") + 1, splittedLine[2].indexOf("}")).split(",");
//
//            if (!neighbors[0].equals("")) {
//                for (String s : neighbors) {
//                    String distance = splittedLine[1];
//                    System.out.println(s + "\t" + (distance.equals("INF") ? "INF" : (Integer.parseInt(distance) + 1)) + "\t" + "{}");
//                }
//            }
//        }

        //Reducer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Map<String, List<Vertex>> vertexMap = new LinkedHashMap<>();

        while (!(line = br.readLine()).trim().equals("")) {
            String[] splittedLine = line.split("\\t");

            String vertexLable = splittedLine[0];
            String distance = splittedLine[1];
            String[] neighbors = splittedLine[2].substring(splittedLine[2].indexOf("{") + 1, splittedLine[2].indexOf("}")).split(",");
            if (neighbors[0].equals("")) {
                neighbors = new String[]{};
            }

            Vertex vertex = new Vertex(vertexLable);
            vertex.setDistance(distance);
            vertex.setNeighbors(Arrays.asList(neighbors));

            if (vertexMap.containsKey(vertexLable)) {
                vertexMap.get(vertexLable).add(vertex);
            } else {
                vertexMap.put(vertexLable, new ArrayList<>(Collections.singletonList(vertex)));
            }
        }

        for (Map.Entry<String, List<Vertex>> entry : vertexMap.entrySet()) {
            int minDistancce = Integer.MAX_VALUE;
            Vertex vertex = new Vertex(entry.getKey());
            for (Vertex v : entry.getValue()) {
                if (isNode(v)) {
                    if (vertex.getDistance() == null) {
                        vertex = v;
                    } else {
                        vertex.setNeighbors(v.getNeighbors());
                    }
                } else {
                    String dist = v.getDistance();
                    if (!dist.equals("INF") && Integer.parseInt(dist) < minDistancce) {
                        minDistancce = Integer.parseInt(dist);
                    }
                    vertex.setDistance(minDistancce == Integer.MAX_VALUE ? "INF" : String.valueOf(minDistancce));
                }
            }
            System.out.println(entry.getKey() + "\t" + vertex.getDistance() + "\t" + "{" + getValuesAsString(vertex.getNeighbors()) + "}");
        }
    }

    private static boolean isNode(Vertex v) {
        return v.getNeighbors().size() > 0;
    }
}
