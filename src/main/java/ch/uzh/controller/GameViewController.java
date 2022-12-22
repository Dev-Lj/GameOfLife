package ch.uzh.controller;
import ch.uzh.controller.fields.GridBoard;
import ch.uzh.model.game.Game;
import ch.uzh.model.game.GameObserver;
import ch.uzh.model.grid.Grid;
import ch.uzh.model.grid.InvalidCellException;
import ch.uzh.model.lobby.Lobby;
import ch.uzh.model.lobby.Player;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class GameViewController implements GameObserver{
    private Game game;

    @FXML private Label lblCurrentPlayer;
    @FXML private Label lblMoveDescription;
    @FXML private Label lblErrMsg;
    @FXML private StackPane stackAnchorGrid;
    @FXML private AnchorPane anchorStatistics;
    private GridBoard gridBoard;

    public void initialize(Lobby lobby, Grid grid) {
        assert lobby != null && grid != null;
        game = new Game(grid, lobby);
        try {
            game.initializeGrid();
        } catch (InvalidCellException e) {
            lblErrMsg.setText("Failed to initialize Grid: " + e.getMessage());
            e.printStackTrace();
        }
        game.attachObserver(this);
        game.initializeMoves();
        initializeGridBoard(grid);
        initializeStatisticsBoard();
    }

    private void initializeGridBoard(Grid grid) {
        gridBoard = new GridBoard(grid, this::cellSelection);
        gridBoard.setAlignment(Pos.CENTER);
        stackAnchorGrid.getChildren().add(gridBoard);
    }

    private void initializeStatisticsBoard() {
        // TODO replace with game statistics
        anchorStatistics.getChildren().add(new Label("Placeholder for Stats"));
    }

    private void cellSelection(int x, int y) {
        lblErrMsg.setText("");
        try {
            game.playerCellSelection(x, y);
        } catch (InvalidCellException e) {
            e.printStackTrace();
            // TODO set errormessage in Grid pls thx
            lblErrMsg.setText(e.getMessage());
        }
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
