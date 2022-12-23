package ch.uzh.view.components;

import java.util.function.Consumer;

import ch.uzh.model.grid.GridObserver;
import ch.uzh.model.lobby.Lobby;
import ch.uzh.model.lobby.LobbyPlayer;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameStatistics extends VBox{
    private final Lobby lobby;
    private final Consumer<LobbyPlayer> winnerConsumer;

    public GameStatistics (Lobby lobby, Consumer<LobbyPlayer> winnerConsumer) {
        this.lobby = lobby;
        this.winnerConsumer = winnerConsumer;
    }

    private static HBox getPlayerStatisticsEntry(LobbyPlayer player) {
        HBox entry = new HBox();
        Rectangle rec = new Rectangle();
        rec.setFill(Color.web(player.getColor()));
        rec.setWidth(20);
        rec.setHeight(20);
        Label lblPlayerLabel = new Label(String.format("%s: %d", player.getName(), player.getAmountOfCells()));
        entry.getChildren().addAll(rec, lblPlayerLabel);
        return entry;
    }

    public void drawScores(int generation) {
        getChildren().clear();
        getChildren().add(new Label(String.format("Generation: %d", generation)));
        for (LobbyPlayer player : lobby.getPlayers()) {
            if (player.getAmountOfCells() == 0) {
                lobby.nextPlayer();
                winnerConsumer.accept(lobby.getCurrentPlayer());
            }
            getChildren().add(getPlayerStatisticsEntry(player));
        }
        
    }
}
