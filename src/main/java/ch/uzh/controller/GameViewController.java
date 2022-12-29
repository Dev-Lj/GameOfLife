package ch.uzh.controller;

import ch.uzh.App;
import ch.uzh.model.game.Game;
import ch.uzh.model.game.GameObserver;
import ch.uzh.model.grid.Grid;
import ch.uzh.model.grid.GridObserver;
import ch.uzh.model.grid.InvalidCellException;
import ch.uzh.model.lobby.LobbyPlayer;
import ch.uzh.view.components.GameStatistics;
import ch.uzh.view.components.GridBoard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class GameViewController implements GameObserver, GridObserver{
    private Game game;

    @FXML private Label lblCurrentPlayer;
    @FXML private Label lblMoveDescription;
    @FXML private Label lblErrMsg;
    @FXML private StackPane stackAnchorGrid;
    @FXML private StackPane stackStatistics;

    private GridBoard gridBoard;
    private GameStatistics statistics;

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
        game.getGrid().attachObserver(this);
        game.initializeMoves();
    }

    /**
     * @pre game != null => initializeData must be called before this
     */
    public void initializeView() {
        assert game != null;
        initializeGridBoard();
        initializeStatisticsBoard();
        updateGrid(game.getGrid());
    }

    /**
     * @pre game != null => initializeData must be called before this
     */
    private void initializeGridBoard() {
        assert game != null;
        gridBoard = new GridBoard(game.getGrid().getSize(), this::cellSelection);
        gridBoard.setAlignment(Pos.CENTER);
        stackAnchorGrid.getChildren().add(gridBoard);
    }

    /**
     * @pre game != null => initializeData must be called before this
     */
    private void initializeStatisticsBoard() {
        assert game != null;
        statistics = new GameStatistics();
        statistics.setAlignment(Pos.CENTER);
        stackStatistics.getChildren().add(statistics);
    }

    public void setWinner(LobbyPlayer winner) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("WinnerView.fxml"));
            Parent p = fxmlLoader.load();
            WinnerViewController wController = fxmlLoader.getController();
            wController.setWinnerName(winner.getName());
            stackStatistics.getScene().setRoot(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public void updateGrid(Grid grid) {
        statistics.drawScores(grid.getGeneration(), game.getLobby());
        gridBoard.draw(grid);
        game.checkForWinner(this::setWinner);
    }
}
