package ch.uzh.controller;
import ch.uzh.model.game.Game;
import ch.uzh.model.game.GameObserver;
import ch.uzh.model.grid.InvalidCellException;
import ch.uzh.model.lobby.LobbyPlayer;
import ch.uzh.view.components.GridBoard;
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

    /**
     * 
     * @param game
     * @throws Exception
     * @pre game != null
     */
    public void initializeData(Game game) throws Exception {
        assert game != null;
        this.game = game;
        try {
            game.initializeGrid();
        } catch (InvalidCellException e) {
            throw new Exception("Failed to initialize grid");
        }
        game.attachObserver(this);
        game.initializeMoves();
    }

    /**
     * @pre game != null => initializeData must be called before this
     */
    public void initializeGridBoard() {
        assert game != null;
        gridBoard = new GridBoard(game.getGrid(), this::cellSelection);
        gridBoard.setAlignment(Pos.CENTER);
        stackAnchorGrid.getChildren().add(gridBoard);
    }

    /**
     * @pre game != null => initializeData must be called before this
     */
    public void initializeStatisticsBoard() {
        assert game != null;
        // TODO replace with game statistics
        anchorStatistics.getChildren().add(new Label("Placeholder for Stats"));
    }

    private void cellSelection(int x, int y) {
        lblErrMsg.setText("");
        try {
            game.playerCellSelection(x, y);
        } catch (InvalidCellException e) {
            lblErrMsg.setText(e.getMessage());
        }
    }

    @Override
    public void nextPlayerTurn(LobbyPlayer currentPlayer) {
        lblCurrentPlayer.setText(currentPlayer.getName());
    }

    @Override
    public void nextMove() {
        lblMoveDescription.setText(game.getCurrentMoveDescription());
    }
}
