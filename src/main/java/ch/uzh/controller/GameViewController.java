package ch.uzh.controller;
import ch.uzh.controller.fields.GridBoard;
import ch.uzh.model.game.Game;
import ch.uzh.model.game.GameObserver;
import ch.uzh.model.grid.Grid;
import ch.uzh.model.lobby.Lobby;
import ch.uzh.model.lobby.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class GameViewController implements GameObserver{
    private Game game;

    @FXML private Label lblCurrentPlayer;
    @FXML private Label lblMoveDescription;
    @FXML private HBox hboxGameContent;

    public void initialize(Lobby lobby, Grid grid) {
        assert lobby != null && grid != null;
        game = new Game(grid, lobby);
        game.attachObserver(this);
        game.initialize();
        initializeGameContent(grid);
    }

    private void initializeGameContent(Grid grid) {
        // TODO replace with game statistics
        AnchorPane placeHolder = new AnchorPane();
        placeHolder.getChildren().add(new Label("Placeholder for Stats"));
        placeHolder.setPrefWidth(64);
        hboxGameContent.getChildren().add(placeHolder);
        GridBoard gridBoard = new GridBoard(grid);
        HBox.setHgrow(gridBoard, Priority.ALWAYS);
        hboxGameContent.getChildren().add(gridBoard);
    }

    @Override
    public void nextPlayerTurn(Player currentPlayer) {
        lblCurrentPlayer.setText(currentPlayer.getName());
    }

    @Override
    public void nextMove() {
        lblMoveDescription.setText(game.getCurrentMoveDescription());
    }
}
