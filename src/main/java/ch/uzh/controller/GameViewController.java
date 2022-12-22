package ch.uzh.controller;
import ch.uzh.model.game.Game;
import ch.uzh.model.game.GameObserver;
import ch.uzh.model.grid.Grid;
import ch.uzh.model.lobby.Lobby;
import ch.uzh.model.lobby.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameViewController implements GameObserver{
    private Game game;

    @FXML
    private Label lblCurrentPlayer;
    @FXML
    private Label lblMoveDescription;

    public void initialize(Lobby lobby, Grid grid) {
        assert lobby != null && grid != null;
        game = new Game(grid, lobby);
        game.attachObserver(this);
        game.initialize();
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
