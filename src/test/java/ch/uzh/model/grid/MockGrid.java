package ch.uzh.model.grid;

public class MockGrid extends Grid{
    private boolean generationComputed = false;

    @Override
    public void computeGeneration() {
        generationComputed = true;
    }

    public boolean isGenerationComputed() {
        return generationComputed;
    }
}
