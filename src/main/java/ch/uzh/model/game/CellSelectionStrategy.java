package ch.uzh.model.game;

@FunctionalInterface
public interface CellSelectionStrategy {
    public void execute(int x, int y);
}
