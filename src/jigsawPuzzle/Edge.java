package jigsawPuzzle;

public class Edge {

    enum Type {
        inner, outer, flat
    }

    Type type;

    boolean matchesWith(Edge edge) {
        return true;
    }
}
