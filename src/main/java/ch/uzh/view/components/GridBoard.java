package ch.uzh.view.components;
import ch.uzh.model.game.CellSelectionStrategy;
import ch.uzh.model.grid.Grid;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridBoard extends GridPane{
    
    private final int dimension;
    private final CellSelectionStrategy cellSelectionStrategy;
    private Rectangle[][] cells;

    public GridBoard(int dimension, CellSelectionStrategy cellSelectionStrategy) {
        this.dimension = dimension;
        this.cellSelectionStrategy = cellSelectionStrategy;
        this.cells = new Rectangle[dimension][dimension];
        initializeGridBoard();
    }

    private void initializeGridBoard() {
        // TODO scale size
        int size = 20;
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
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
     * @pre grid.getDimension() == this.dimension
     */
    public void draw(Grid grid) {
        assert grid.getDimension() == this.dimension;
        String[][] drawableGrid = grid.getDrawableGrid();
        for (int x = 0; x < drawableGrid.length; x++) {
            for (int y = 0; y < drawableGrid.length; y++) {
                cells[x][y].setFill(Color.web(drawableGrid[x][y]));
            }
        }
    }    
}
