package ch.uzh.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import ch.uzh.App;
import ch.uzh.controller.components.PlayerconfigController;
import ch.uzh.model.game.Game;
import ch.uzh.model.grid.Grid;
import ch.uzh.model.lobby.Lobby;
import ch.uzh.model.lobby.LobbyPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerconfigViewController implements Initializable {
    @FXML
    private Label errorText;

    @FXML
    private VBox vboxPlayerConfigList;
    private List<PlayerconfigController> playerInputControllers = new ArrayList<PlayerconfigController>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < Lobby.DEFAULT_NR_PLAYERS; i++) {
            PlayerconfigController playerCont = new PlayerconfigController(i + 1);
            playerInputControllers.add(playerCont);
            vboxPlayerConfigList.getChildren().addAll(playerCont.getItem());
        }
    }

    @FXML
    private void updatePlayers() {
        for (PlayerconfigController playerconfig : playerInputControllers) {
            playerconfig.updatePlayer();
        }
    }

    private Lobby createLobby() throws IllegalArgumentException{
         LobbyPlayer[] players = playerInputControllers.stream().map(input->input.getLobbyPlayer()).toArray(size->new LobbyPlayer[size]);
        return new Lobby(players);
    }

    @FXML
    public void goNext(ActionEvent event) {
        updatePlayers();
        try {
            Lobby lobby = createLobby();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("GameView.fxml"));
                Parent p = fxmlLoader.load();
                GameViewController gController = fxmlLoader.getController();
                Game game = new Game(new Grid(20), lobby);
                // TODO handle possible exception
                gController.initializeData(game);
                gController.initializeView();
                Scene rootScene = ((Node) event.getSource()).getScene();
                rootScene.setRoot(p);
            } catch (Exception e) {
                e.printStackTrace();
                errorText.setText(e.getMessage());
            }
        } catch (Exception e) {
            errorText.setText(e.getMessage());
        }
    }

}
