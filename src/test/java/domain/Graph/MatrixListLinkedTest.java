package domain.Graph;

import domain.list.ListException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixListLinkedTest {
    private AdjacencyMatrixGraph adjacencyMatrixGraph;
    private AdjacencyListGraph adjacencyListGraph;
    private SinglyLinkedListGraph singlyLinkedListGraph;

    public MatrixListLinkedTest() {
        try {
            this.adjacencyMatrixGraph = new AdjacencyMatrixGraph(20);
            this.adjacencyListGraph = new AdjacencyListGraph(20);
            this.singlyLinkedListGraph = new SinglyLinkedListGraph();

            //filling graphs
            for (int i = 0; i < 20; i++) {
                int value = util.Utility.random(99);
                this.adjacencyMatrixGraph.addVertex(value);
                this.adjacencyListGraph.addVertex(value);
                this.singlyLinkedListGraph.addVertex(value);
            }

            //adding edges
            for(int i = 0; i < 20; i++) {
                for(int j = 0; j < 20; j++) {
                    if(i!=j&&util.Utility.random(5)==1){
                        adjacencyMatrixGraph.addEdgeAndWeight(adjacencyMatrixGraph.getVertexByIndex(i).data,
                                adjacencyMatrixGraph.getVertexByIndex(j).data,
                                util.Utility.random(1, 50));
                        adjacencyListGraph.addEdgeAndWeight(adjacencyListGraph.getVertexByIndex(i).data,
                                adjacencyListGraph.getVertexByIndex(j).data,
                                util.Utility.random(1, 50));
                        singlyLinkedListGraph.addEdgeAndWeight(singlyLinkedListGraph.getVertexByIndex(i+1).data,
                                singlyLinkedListGraph.getVertexByIndex(j+1).data,
                                util.Utility.random(1, 50));
                    }
                }
            }
        }catch(GraphException | ListException ex){
            throw new RuntimeException(ex);
        }
    }

    @Test
    void test() {
        try{
            System.out.println("VERTEX DEGREE\n");
            System.out.println("Matrix Graph: "+getVertexDegree(this.adjacencyMatrixGraph));
            System.out.println("List Graph: "+getVertexDegree(this.adjacencyListGraph));
            System.out.println("Linked Graph: "+getVertexDegree(this.singlyLinkedListGraph));

            System.out.println("GRAPH DEGREE\n");
            System.out.println("Matrix Graph: "+getGraphDegree(this.adjacencyMatrixGraph));
            System.out.println("List Graph: "+getGraphDegree(this.adjacencyListGraph));
            System.out.println("Linked Graph: "+getGraphDegree(this.singlyLinkedListGraph));

            System.out.println("GRAPH TOTAL EDGES\n");
            System.out.println("Matrix Graph: "+totalEdges(this.adjacencyMatrixGraph));
            System.out.println("List Graph: "+totalEdges(this.adjacencyListGraph));
            System.out.println("Linked Graph: "+totalEdges(this.singlyLinkedListGraph));

            System.out.println("GRAPH VERTICES TOTAL EDGES\n");
            System.out.println("Matrix Graph: "+showEdges(this.adjacencyMatrixGraph, "TotalEdges"));
            System.out.println("List Graph: "+showEdges(this.adjacencyListGraph, "TotalEdges"));
            System.out.println("Linked Graph: "+showEdges(this.singlyLinkedListGraph, "TotalEdges"));

            System.out.println("GRAPH VERTICES LIST OF EDGES\n");
            System.out.println("Matrix Graph: "+showEdges(this.adjacencyMatrixGraph, "getEdges"));
            System.out.println("List Graph: "+showEdges(this.adjacencyListGraph, "getEdges"));
            System.out.println("Linked Graph: "+showEdges(this.singlyLinkedListGraph, "getEdges"));


        }catch(GraphException | ListException ex){
            throw new RuntimeException(ex);
        }
    }

    private String getVertexDegree(Graph graph) throws GraphException, ListException {
        String result="";
        int n = graph.size();
        for (int i = 0; i < n; i++) {
            Object value;
            if(graph instanceof AdjacencyMatrixGraph ||
            graph instanceof AdjacencyListGraph)
                value = graph.getVertexByIndex(i).data;
            else value = graph.getVertexByIndex(i+1).data;
            result+="\nVertex degree of "+value+": "+graph.getVertexDegree(value);
        }
        return result;
    }

    private String getGraphDegree(Graph graph) throws GraphException, ListException {
        return graph.getGraphDegree();
    }

    private int totalEdges(Graph graph) throws GraphException, ListException {
        return graph.totalEdges();
    }

    private String showEdges(Graph graph, String name) throws ListException, GraphException {
        String result="";
        int n = graph.size();
        for (int i = 0; i < n; i++) {
            Object value;
            if(graph instanceof AdjacencyMatrixGraph ||
                    graph instanceof AdjacencyListGraph)
                value = graph.getVertexByIndex(i).data;
            else value = graph.getVertexByIndex(i+1).data;
            switch (name) {
                case "TotalEdges":
                    result += "\nTotal edges of vertex " + value + ": " + graph.totalEdges(value);
                    break;
                case "getEdges":
                    result += "\nList of edges for the vertex " + value + ": " + graph.getEdges(value);
            }
        }
        return result;
    }
}