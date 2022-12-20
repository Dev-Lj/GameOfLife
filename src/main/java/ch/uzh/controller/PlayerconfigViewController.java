package ch.uzh.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import ch.uzh.App;
import ch.uzh.controller.components.PlayerconfigController;
import ch.uzh.model.lobby.Lobby;
import ch.uzh.model.lobby.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private ArrayList<Player> players = new ArrayList<Player>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < Lobby.NR_PLAYERS; i++) {
            PlayerconfigController playerCont = new PlayerconfigController(i + 1);
            players.add(playerCont.getPlayer());
            playerInputControllers.add(playerCont);
            vboxPlayerConfigList.getChildren().addAll(playerCont.getField());
        }
    }

    /*
     * private boolean userCompleted() {
     * 
     * lobby = new Lobby();
     * for (PlayerconfigController playerconfig : playerInputControllers) {
     * playerconfig.updatePlayer();
     * lobby.setPlayer(playerconfig.getPlayer());
     * }
     * if (lobby.validPlayer() == false) {
     * lobby.getErrors();
     * errorText.setText(lobby.getErrors());
     * return false;
     * } else {
     * errorText.setText("");
     * return true;
     * }
     * }
     */

    @FXML
    private void updatePlayers() {
        for (PlayerconfigController playerconfig : playerInputControllers) {
            playerconfig.updatePlayer();
        }
    }

    @FXML
    public void goNext(ActionEvent event) {
        updatePlayers();
        try {
            Lobby lobby = new Lobby(players);
            try {
                Parent root = App.loadFXML("components/Grid");
                Scene rootScene = ((Node) event.getSource()).getScene();
                rootScene.setRoot(root);
            } catch (Exception e) {
                e.printStackTrace();
                errorText.setText(e.getMessage());
            }
        } catch (Exception e) {
            errorText.setText(e.getMessage());
        }
    }

    // TODO research onChange update handlders for Text & Combobox
}
