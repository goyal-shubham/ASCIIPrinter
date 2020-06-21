package jigsawPuzzle;

import java.util.List;

public class Puzzle {

    Piece[] pieces;
    List<Edge> innerEdge;
    List<Edge> outerEdge;
    List<Edge> flatEdge;
    List<Edge> pendingEdge;


    void addEdgeToList(Edge edge) {
        if(edge.type == Edge.Type.inner) {
            this.innerEdge.add(edge);
            pendingEdge.add(edge);
        } else if(edge.type == Edge.Type.outer) {
            this.outerEdge.add(edge);
            pendingEdge.add(edge);
        }else {
            this.flatEdge.add(edge);
        }

    }
    void addEdge(Piece p) {

        addEdgeToList(p.left);
        addEdgeToList(p.right);
        addEdgeToList(p.top);
        addEdgeToList(p.bottom);
    }

    public Puzzle(Piece[] pieces) {
        this.pieces = pieces;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(new Piece[0]); // pass array of pieces

        for(Piece p : puzzle.pieces) {
            puzzle.addEdge(p);
        }

        for(Edge edge: puzzle.pendingEdge) {

            if(edge.type == Edge.Type.inner) {
                for(Edge outEdge : puzzle.outerEdge) {
                    if(edge.matchesWith(outEdge)) {
                        puzzle.pendingEdge.remove(edge);
                        puzzle.pendingEdge.remove(outEdge);
                        puzzle.outerEdge.remove(outEdge);
                    }
                }
            } else if(edge.type == Edge.Type.outer) { // TODO - figure out if this is required.
                for(Edge inEdge : puzzle.innerEdge) {
                    if(edge.matchesWith(inEdge)) {
                        puzzle.pendingEdge.remove(edge);
                        puzzle.pendingEdge.remove(inEdge);
                        puzzle.innerEdge.remove(inEdge);
                    }
                }
            }
        }
    }


}
