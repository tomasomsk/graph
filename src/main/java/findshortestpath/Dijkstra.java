package findshortestpath;

import java.util.*;

class Dijkstra {

    private static List<String> processed = new ArrayList<>();      // "Ферма" маркеров посещённости

    public LinkedHashMap<String, Integer> getShortestPath(Graph graph, String toNode) {
        Integer graphMatrix[][] = graph.getMatrix();
        List<Vertex> vertexes = graph.getVertexList();

        Map<String, Integer> costs = getCostsOfNodesAfterStartNode(graphMatrix, vertexes);
        Map<String, String> parents = getParentsOfNodesAfterStartNode(graphMatrix, vertexes);

        String node = getLowestCostNode(costs);
        while (node != null) {
            Integer cost = costs.get(node);
            Map<String, Integer> neighbors = graph.getNeighbors(node);
            for (String n : neighbors.keySet()) {
                Integer newCost = cost + neighbors.get(n);
                if (costs.get(n) > newCost) {
                    costs.put(n, newCost);
                    parents.put(n, node);
                }
            }
            Dijkstra.processed.add(node);
            node = getLowestCostNode(costs);
        }
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        result.put(toNode, costs.get(toNode));
        String parentNode = parents.get(toNode);
        while (parentNode != null) {
            result.put(parentNode, costs.get(parentNode) == null ? 0 : costs.get(parentNode));
            parentNode = parents.get(parentNode);
        }
        return result;
    }

    public LinkedHashMap<String, Integer> getShortestPathFromNodeToNode(Graph graph, String fromNode, String toNode) {
        Integer graphMatrix[][] = graph.getMatrix();
        List<Vertex> vertexes = graph.getVertexList();

        Map<String, Integer> costs = getCostsOfNodesAfterNode(fromNode, graphMatrix, vertexes);
        Map<String, String> parents = getParentsOfNodesAfterNode(fromNode, graphMatrix, vertexes);

        String node = getLowestCostNode(costs);
        while (node != null) {
            Integer cost = costs.get(node);
            Map<String, Integer> neighbors = graph.getNeighbors(node);
            for (String n : neighbors.keySet()) {
                Integer newCost = cost + neighbors.get(n);
                if (costs.get(n) > newCost) {
                    costs.put(n, newCost);
                    parents.put(n, node);
                }
            }
            Dijkstra.processed.add(node);
            node = getLowestCostNode(costs);
        }
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        result.put(toNode, costs.get(toNode));
        String parentNode = parents.get(toNode);
        while (parentNode != null) {
            result.put(parentNode, costs.get(parentNode) == null ? 0 : costs.get(parentNode));
            parentNode = parents.get(parentNode);
        }
        return result;
    }


    private Map<String, Integer> getCostsOfNodesAfterStartNode(Integer[][] graphMatrix, List<Vertex> vertexList) {
        return getCostsOfNodesAfterNode(vertexList.get(0).getStringLabel(), graphMatrix, vertexList);
    }

    private Map<String, Integer> getCostsOfNodesAfterNode(String node, Integer[][] graphMatrix, List<Vertex> vertexList) {
        Map<String, Integer> costs = new HashMap<>();
        costs.put(node, 0);
        for (int i = 0; i < graphMatrix.length; i++) {
            if (node.equals(vertexList.get(i).getStringLabel())) {
                for (int k = i; k < graphMatrix.length; k++) {
                    for (int j = 0; j < graphMatrix.length; j++) {
                        if (k == i && graphMatrix[k][j] != 0) {
                            costs.put(vertexList.get(j).getStringLabel(), graphMatrix[k][j]);
                        } else if (graphMatrix[k][j] != 0 && !vertexList.get(j).isVisited()) {
                            costs.putIfAbsent(vertexList.get(j).getStringLabel(), Integer.MAX_VALUE);
                        }
                    }
                    if (costs.get(vertexList.get(k).getStringLabel()) != null) {
                        vertexList.get(k).setVisited(true);
                    }
                }
                break;
            }
        }
        return costs;
    }

    private Map<String, String> getParentsOfNodesAfterStartNode(Integer[][] graphMatrix, List<Vertex> vertexList) {
        return getParentsOfNodesAfterNode(vertexList.get(0).getStringLabel(), graphMatrix, vertexList);
    }

    private Map<String, String> getParentsOfNodesAfterNode(String node, Integer[][] graphMatrix, List<Vertex> vertexList) {
        Map<String, String> parents = new HashMap<>();
        for (int i = 0; i < graphMatrix.length; i++) {
            if (node.equals(vertexList.get(i).getStringLabel())) {
                for (int k = i; k < graphMatrix.length; k++) {
                    for (int j = 0; j < graphMatrix.length; j++) {
                        if (k == i && graphMatrix[k][j] != 0) {
                            parents.putIfAbsent(vertexList.get(j).getStringLabel(), vertexList.get(k).getStringLabel());
                        }
                    }
                }
                break;
            }
        }
        return parents;
    }

    private String getLowestCostNode(Map<String, Integer> costs) {
        Integer lowestCost = Integer.MAX_VALUE;
        String lowestCostNode = null;
        for (String node : costs.keySet()) {
            Integer cost = costs.get(node);
            if (cost < lowestCost && !processed.contains(node)) {
                lowestCost = cost;
                lowestCostNode = node;
            }
        }
        return lowestCostNode;
    }
}

