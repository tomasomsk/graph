package findshortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    //массив для хранения вершин
    private List<Vertex> vertexList;
    //матрица смежности
    private Integer[][] matrix;


    public Graph(int vertexMaxCount) {
        //максимальное количество вершин в графе
        matrix = new Integer[vertexMaxCount][vertexMaxCount];
        //перед началом работы заполняем матрицу смежности нулями
        for (int i = 0; i < vertexMaxCount; i++)
            for (int j = 0; j < vertexMaxCount; j++)
                matrix[i][j] = 0;
        vertexList = new ArrayList<>();
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public Integer[][] getMatrix() {
        return matrix;
    }

    //добавление вершины
    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    //добавление ребра
    public void addEdge(int begin, int end) {
        matrix[begin][end] = 1;
        matrix[end][begin] = 0;
    }

    //добавление ребра с весом
    public void addEdge(int begin, int end, int weight) {
        matrix[begin][end] = weight;
//        matrix[end][begin] = 0;
    }

    public Map<String, Integer> getNeighbors(String vertexLabel) {
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            if (vertexLabel.equals(vertexList.get(i).getStringLabel())) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j] != 0) {
                        result.put(vertexList.get(j).getStringLabel(), matrix[i][j]);
                    }
                }
            }
        }
        return result;
    }

    public Map<String, Integer> getNeighbors(Integer vertexNumber) {
        Map<String, Integer> result = new HashMap<>();
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[vertexNumber][j] != 0) {
                result.put(vertexList.get(j).getStringLabel(), matrix[vertexNumber][j]);
            }
        }
        return result;
    }
}
