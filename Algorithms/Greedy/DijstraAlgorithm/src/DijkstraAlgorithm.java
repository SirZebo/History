import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DijkstraAlgorithm<T> {

    public void calculateShortestPath(Node<T> src) {
        src.setDistance(0);
        Set<Node<T>> settledNodes = new HashSet<>();
        Queue<Node<T>> unsettledNodes = new PriorityQueue<>(Collections.singleton(src));

        while (!unsettledNodes.isEmpty()) {
            Node<T> curr = unsettledNodes.remove();
            curr.getNeighbors()
                    .entrySet().stream()
                    .filter(entry -> !settledNodes.contains(entry.getKey()))
                    .forEach(entry -> {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), curr);
                        unsettledNodes.add(entry.getKey());
                    });
            settledNodes.add(curr);
        }
    }

    private void evaluateDistanceAndPath(Node<T> next, Integer edgeWeight, Node<T> curr) {
        Integer newDistance = curr.getDistance() + edgeWeight;
        if (newDistance < next.getDistance()) {
            next.setDistance(newDistance);
            next.setShortestPath(
                    Stream.concat(curr.getShortestPath().stream(), Stream.of(curr)).toList()
            );
        }
    }

    public void printPaths(List<Node<T>> nodes) {
        nodes.forEach(node -> {
            String path = node.getShortestPath().stream()
                    .map(Node::toString)
                    .collect(Collectors.joining(" -> "));
            System.out.println((path.isBlank()
                    ? "%s : %s".formatted(node.toString(), node.getDistance())
                    : "%s -> %s : %s".formatted(path, node.toString(), node.getDistance()))
            );
        });
    }

    public static void main(String[] args) {
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");
        Node<String> nodeF = new Node<>("F");

        nodeA.addNeighbor(nodeB, 2);
        nodeA.addNeighbor(nodeC, 4);

        nodeB.addNeighbor(nodeC, 3);
        nodeB.addNeighbor(nodeD, 1);
        nodeB.addNeighbor(nodeE, 5);

        nodeC.addNeighbor(nodeD, 2);

        nodeD.addNeighbor(nodeE, 1);
        nodeD.addNeighbor(nodeF, 4);

        nodeE.addNeighbor(nodeF, 2);

        DijkstraAlgorithm<String> mst = new DijkstraAlgorithm<>();
        mst.calculateShortestPath(nodeA);
        mst.printPaths(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF));
    }
}

