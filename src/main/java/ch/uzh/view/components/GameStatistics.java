package ch.uzh.view.components;

import ch.uzh.model.lobby.Lobby;
import ch.uzh.model.lobby.LobbyPlayer;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameStatistics extends VBox{
    private static HBox getPlayerStatisticsEntry(LobbyPlayer player) {
        HBox entry = new HBox();
        Rectangle rec = new Rectangle();
        rec.setFill(Color.web(player.getColor()));
        rec.setWidth(20);
        rec.setHeight(20);
        Label lblPlayerLabel = new Label(String.format("%s:  %d", player.getName(), player.getAmountOfCells()));
        entry.getChildren().addAll(rec, lblPlayerLabel);
        entry.setSpacing(10);
        //entry.getStylesheets().add(getClass().getResource("subscene.css").toExternalForm());
        return entry;
    }

    public void drawScores(int generation, Lobby lobby) {
        getChildren().clear();
        Label GenLabel = new Label(String.format("Generation: %d", generation));
        getChildren().add(GenLabel);
        for (LobbyPlayer player : lobby.getPlayers()) {
            getChildren().add(getPlayerStatisticsEntry(player));
        }
        setSpacing(10);
        //((Labeled) getChildren()).setAlignment(Pos.TOP_CENTER);
        
    }
}
