package ch.uzh.model.grid;

public class MockGridObserver implements GridObserver{
    private boolean updated = false;
    private Grid updatedGrid = null;

    public boolean wasUpdated() {
        return updated;
    }

    public Grid getUpdatedGrid() {
        return updatedGrid;
    }

    @Override
    public void updateGrid(Grid grid) {
        updated = true;
        this.updatedGrid = grid;
        
    }
    
}
