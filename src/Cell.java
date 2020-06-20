import java.util.HashSet;
import java.util.Stack;

class Cell {

    Stack<Integer> colors;

    public Cell() {
        this.colors = new Stack<>();
    }

    void addNewGrid(Integer grid) {
        colors.add(grid);
    }

    public void bringToFront(Integer id) {
        if(colors.contains(id)) {
            colors.remove(id);
            colors.push(id);
        }
    }
    int getTopGridId() {
        if(colors.isEmpty()) {
            return -1;
        }
        return colors.peek();
    }
}
