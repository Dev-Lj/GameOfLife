package ch.uzh.model.grid;

public class MockGrid extends Grid{

    public MockGrid(int size) {
        super(size);
    }

    private boolean generationComputed = false;
    private boolean observerAttached = false;

    @Override
    public void computeGeneration() {
        generationComputed = true;
    }

    @Override
    public void attachObserver(GridObserver observer) {
        this.observerAttached = true;
    }

    public boolean isGenerationComputed() {
        return generationComputed;
    }

    public boolean isObserverAttached() {
        return observerAttached;
    }
}
