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
    Lobby lobby;

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
            lobby = new Lobby(players);
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

    public Lobby getLobby() {
        return lobby;
    }

    // TODO research onChange update handlders for Text & Combobox

}
