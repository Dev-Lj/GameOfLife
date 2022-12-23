package ch.uzh.model.grid;

public class StubGrid extends Grid{
    private boolean generationComputed = false;

    @Override
    public void computeGeneration() {
        generationComputed = true;
    }

    public boolean isGenerationComputed() {
        return generationComputed;
    }
}
