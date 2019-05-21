package findshortestpath;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private String stringLabel;
    private boolean isVisited;
    private String distance;
    private List<String> neighbors;
    private String pageRank;
    private double probability;

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public Vertex(String stringLabel) {
        this.stringLabel = stringLabel;
        isVisited = false;
    }

    public String getPageRank() {
        return pageRank;
    }

    public void setPageRank(String pageRank) {
        this.pageRank = pageRank;
    }

    public String getStringLabel() {
        return stringLabel;
    }

    public void setStringLabel(String stringLabel) {
        this.stringLabel = stringLabel;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<String> getNeighbors() {
        if (neighbors == null) {
            neighbors = new ArrayList<>();
        }
        return neighbors;
    }

    public void setNeighbors(List<String> neighbors) {
        if (neighbors == null) {
            neighbors = new ArrayList<>();
        }
        this.neighbors = neighbors;
    }

    public void addNeighbor(String neighbor) {
        if (neighbors == null) {
            neighbors = new ArrayList<String>();
        }
        neighbors.add(neighbor);
    }

    public static String getValuesAsString(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i));
            if (i != list.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }
}
