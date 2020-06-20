import java.util.*;

public class Canvas {

    Cell[][] cells;
    int rows;
    int cols;
    Map<Integer, Grid> grids;
    int gridId = 0;

    Canvas() {
        this.cells = new Cell[8][8];
        rows = 8;
        cols = 8;
        grids = new HashMap<>();
        init();
    }

    Canvas(int x, int y){
        this.cells = new Cell[x][y];
        cols = x;
        rows = y;
        grids = new HashMap<>();
        init();
    }

    public void init() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public static void main(String[] args) {
        Canvas canvas =  new Canvas();
        canvas.updateColor(0,0,2,2, "W");
        canvas.updateColor(0,5,3,2, "R");
        canvas.updateColor(1,5,3,2, "W");
        canvas.printCanvas();
        canvas.bringToFront(0,5);
        canvas.printCanvas();
        canvas.bringToFront(3,5);
        canvas.printCanvas();
        canvas.move(0,1,1,1);
        canvas.printCanvas();

        canvas.erase(0,0);
        canvas.printCanvas();
        canvas.erase(1,1);
        canvas.printCanvas();
    }



    /*
    T1 : Color from start position for the given rectangle.
     */
    public void updateColor(int row, int col, int length, int width, String color ) {

        Grid grid = new Grid(row, col, length, width, color);
        grids.put(gridId, grid);
        for(int i = row; i < row + length; i++) {
            for(int j = col; j < col + width; j++) {
                Cell currentCell = cells[i][j];
                currentCell.colors.add(gridId);
            }
        }
        gridId++;

    }

    /*
T2 : Bring specific rectangle to top.
 */
    private void bringToFront(int row, int col) {
        Integer gridId = cells[row][col].getTopGridId();
        Grid grid = grids.get(gridId);
        for(int i = grid.row; i < grid.row + grid.length; i++) {
            for(int j = grid.col; j < grid.col + grid.width; j++ ) {
                Cell cell = cells[i][j];
                if(cell.getTopGridId() != gridId) {
                    cell.bringToFront(gridId);
                }

            }
        }
    }

    /*
 T3 : Move grid
 TODO : what if given position is not the top left??
  */
    private void move(int row, int col, int dRow, int dCol) {
        int gridId = cells[row][col].getTopGridId();
        if(gridId != -1) {
            Grid grid = grids.get(gridId);
            for(int i = grid.row; i < grid.row + grid.length; i++) {
                for(int j = grid.col; j < grid.col + grid.width; j++) {
                    cells[i][j].colors.remove(gridId);
                }
            }
            for(int i = dRow; i < dRow + grid.length; i++) {
                for(int j = dCol; j < dCol + grid.width; j++) {
                    cells[i][j].colors.push(gridId);
                }
            }
            grid.row = dRow;
            grid.col = dCol;
        }
    }

    /*
T4: Erase grid
*/
    private void erase(int row, int col) {
        int gridId = cells[row][col].getTopGridId();
        if(gridId != -1) {
            Grid grid = grids.get(gridId);
            for(int i = grid.row; i < grid.row + grid.length; i++) {
                for(int j = grid.col; j < grid.col + grid.width; j++) {
                    cells[i][j].colors.remove(gridId);
                }
            }

            grids.remove(gridId);
        }
    }

    private void printCanvas() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {

                int id = cells[i][j].getTopGridId();
                if(id == -1) {
                    System.out.print( "X ");
                } else {
                    System.out.print( grids.get(id).color + " ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------------");
    }
}
