package ch.uzh.controller.fields;
import ch.uzh.model.game.CellSelectionStrategy;
import ch.uzh.model.grid.Grid;
import ch.uzh.model.grid.GridObserver;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridBoard extends GridPane implements GridObserver{
    
    private final Grid grid;
    private final CellSelectionStrategy cellSelectionStrategy;
    private Rectangle[][] cells;

    public GridBoard(Grid grid, CellSelectionStrategy cellSelectionStrategy) {
        this.grid = grid;
        this.cellSelectionStrategy = cellSelectionStrategy;
        this.cells = new Rectangle[grid.getDimension()][grid.getDimension()];
        initializeGridBoard();
        draw();
        grid.attachObserver(this);
    }

    private void initializeGridBoard() {
        // TODO scale size
        int size = 20;
        for (int x = 0; x < grid.getDimension(); x++) {
            for (int y = 0; y < grid.getDimension(); y++) {
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

    public void draw() {
        String[][] drawableGrid = grid.getDrawableGrid();
        for (int x = 0; x < drawableGrid.length; x++) {
            for (int y = 0; y < drawableGrid.length; y++) {
                cells[x][y].setFill(Color.web(drawableGrid[x][y]));
            }
        }
    }

    @Override
    public void notifyUpdate() {
        draw();
    }
    
}
