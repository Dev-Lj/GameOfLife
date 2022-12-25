package ch.uzh.controller;
import java.lang.ModuleLayer.Controller;

import ch.uzh.App;
import ch.uzh.model.game.Game;
import ch.uzh.model.game.GameObserver;
import ch.uzh.model.grid.GridObserver;
import ch.uzh.model.grid.InvalidCellException;
import ch.uzh.model.lobby.LobbyPlayer;
import ch.uzh.view.components.GameStatistics;
import ch.uzh.view.components.GridBoard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameViewController implements GameObserver, GridObserver{
    private Game game;

    @FXML private Label lblCurrentPlayer;
    @FXML private Label lblMoveDescription;
    @FXML private Label lblErrMsg;
    @FXML private StackPane stackAnchorGrid;
    @FXML private AnchorPane anchorStatistics;
    @FXML private Label winner_name;
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
        statistics = new GameStatistics(game.getLobby(), this::setWinner);
        statistics.setAlignment(Pos.CENTER);
        anchorStatistics.getChildren().add(statistics);
    }

    public void setWinner(LobbyPlayer winner) {
        // TODO proper winning screen
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AnnounceWinner.fxml"));
            Parent p = fxmlLoader.load();
            //Controller aController = fxmlLoader.getController();
            winner_name.setText(winner.getName());
            // TODO Get CURRENT stage to set scene
            Stage winnerStage = new Stage();
            winnerStage.setTitle("Conway's Game of Life");
            winnerStage.setScene(new Scene(p, 800, 700));
            winnerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            //errorText.setText(e.getMessage());
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
    public void notifyUpdate() {
        statistics.drawScores(0);
        gridBoard.draw();
    }
}
