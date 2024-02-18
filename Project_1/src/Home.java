import java.util.*;

public class Home {
    private List<List<Double>> weights;
    private int numLayers;

    public Home(int numLayers, List<Integer> nodesPerLayer) {
        this.numLayers = numLayers;
        this.weights = new ArrayList<>();

        for (int i = 0; i < numLayers - 1; i++) {
            List<Double> layerWeights = new ArrayList<>();
            for (int j = 0; j < nodesPerLayer.get(i); j++) {
                for (int k = 0; k < nodesPerLayer.get(i + 1); k++) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.printf("Enter the weight of edge between node %d in layer %d and node %d in layer %d: ", j + 1, i + 1, k + 1, i + 2);
                    double weight = scanner.nextDouble();
                    layerWeights.add(weight);
                }
            }
            weights.add(layerWeights);
        }
    }

    public double getWeight(int fromNode, int fromLayer, int toNode, int toLayer) {
        return weights.get(fromLayer - 1).get((fromNode - 1) * getNodesInLayer(fromLayer) + toNode - 1);
    }

    private int getNodesInLayer(int layer) {
        return weights.get(layer - 1).size() / (layer == 1 ? 1 : weights.get(layer - 2).size());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of layers:");
        int numLayers = scanner.nextInt();

        List<Integer> nodesPerLayer = new ArrayList<>();
        for (int i = 0; i < numLayers; i++) {
            System.out.printf("Enter the number of nodes in layer %d: ", i + 1);
            int numNodes = scanner.nextInt();
            nodesPerLayer.add(numNodes);
        }

        Home neuralNetwork = new Home(numLayers, nodesPerLayer);

        System.out.println("Enter the nodes and layer to query the weight of an edge (e.g., 1 1 2 2):");
        int fromNode = scanner.nextInt();
        int fromLayer = scanner.nextInt();
        int toNode = scanner.nextInt();
        int toLayer = scanner.nextInt();

        double weight = neuralNetwork.getWeight(fromNode, fromLayer, toNode, toLayer);
        System.out.printf("The weight of the edge between node %d in layer %d and node %d in layer %d is %.2f\n", fromNode, fromLayer, toNode, toLayer, weight);
    }
}

//import java.util.Scanner;
//
//public class Home {
//    private int[][] adjacencyMatrix;
//    private int numNodes;
//
//    public Home(int numLayers, int[] numNodesPerLayer) {
//        this.numNodes = 0;
//        for (int i = 0; i < numLayers; i++) {
//            this.numNodes += numNodesPerLayer[i];
//        }
//        this.adjacencyMatrix = new int[this.numNodes][this.numNodes];
//    }
//
//    public void setEdgeWeight(int sourceNode, int destinationNode, int weight) {
//        this.adjacencyMatrix[sourceNode][destinationNode] = weight;
//    }
//
//    public int getEdgeWeight(int sourceNode, int destinationNode) {
//        return this.adjacencyMatrix[sourceNode][destinationNode];
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter the number of layers: ");
//        int numLayers = scanner.nextInt();
//
//        int[] numNodesPerLayer = new int[numLayers];
//        for (int i = 0; i < numLayers; i++) {
//            System.out.println("Enter the number of nodes in layer " + (i + 1) + ": ");
//            numNodesPerLayer[i] = scanner.nextInt();
//        }
//
//        Home graph = new Home(numLayers, numNodesPerLayer);
//
//        for (int i = 0; i < graph.numNodes; i++) {
//            for (int j = 0; j < graph.numNodes; j++) {
//                System.out.println("Enter weight of edge from node " + (i+1) + " to node " + (j+1) + ": ");
//                int weight = scanner.nextInt();
//                graph.setEdgeWeight(i, j, weight);
//            }
//        }
//
//        System.out.println("Enter source node: ");
//        int sourceNode = scanner.nextInt();
//        System.out.println("Enter destination node: ");
//        int destinationNode = scanner.nextInt();
//
//        int edgeWeight = graph.getEdgeWeight(sourceNode, destinationNode);
//        System.out.println("The weight of the edge from node " + sourceNode + " to node " + destinationNode + " is: " + edgeWeight);
//    }
//}
