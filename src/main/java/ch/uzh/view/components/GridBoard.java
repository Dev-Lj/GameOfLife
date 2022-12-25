package ch.uzh.view.components;
import ch.uzh.model.game.CellSelectionStrategy;
import ch.uzh.model.grid.Grid;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridBoard extends GridPane{
    
    private final int size;
    private final CellSelectionStrategy cellSelectionStrategy;
    private Rectangle[][] cells;

    public GridBoard(int size, CellSelectionStrategy cellSelectionStrategy) {
        this.size = size;
        this.cellSelectionStrategy = cellSelectionStrategy;
        this.cells = new Rectangle[size][size];
        initializeGridBoard();
    }

    private void initializeGridBoard() {
        // TODO scale size
        int size = 20;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(size);
                rec.setHeight(size);
                rec.setStroke(Color.BLACK);
                rec.setStrokeWidth(1);
                rec.setOnMouseClicked(evt -> cellSelectionStrategy.execute(getColumnIndex(rec), getRowIndex(rec)));
                add(rec, x, y);
                cells[x][y] = rec;
            }
        }
    }

    /**
     * 
     * @pre grid.getDimension() == this.size
     */
    public void draw(Grid grid) {
        assert grid.getSize() == this.size;
        String[][] drawableGrid = grid.getDrawableGrid();
        for (int x = 0; x < drawableGrid.length; x++) {
            for (int y = 0; y < drawableGrid.length; y++) {
                cells[x][y].setFill(Color.web(drawableGrid[x][y]));
            }
        }
    }    
}
