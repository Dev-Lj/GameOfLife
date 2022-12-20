package ch.uzh.controller;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerconfigViewController implements Initializable {
    private Lobby lobby;

    @FXML
    private Label errorText;

    @FXML
    private VBox vboxPlayerConfigList;
    private List<PlayerconfigController> playerInputControllers = new ArrayList<PlayerconfigController>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < Lobby.NR_PLAYERS; i++) {
            PlayerconfigController player = new PlayerconfigController(i + 1);
            playerInputControllers.add(player);
            vboxPlayerConfigList.getChildren().addAll(player.getField());
        }
    }

    private boolean userCompleted() {
        lobby = new Lobby();
        for (PlayerconfigController playerconfig : playerInputControllers) {
            playerconfig.updatePlayer();
            lobby.setPlayer(playerconfig.getPlayer());
        }
        if (lobby.validPlayer() == false) {
            lobby.getErrors();
            errorText.setText(lobby.getErrors());
            return false;
        } else {
            errorText.setText("");
            return true;
        }
    }

    @FXML
    public void goNext(ActionEvent event) {
        if (userCompleted() == true) {
            try {
                Parent root = App.loadFXML("components/Grid");
                Scene rootScene = ((Node) event.getSource()).getScene();
                rootScene.setRoot(root);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        /*
         * for (PlayerconfigController playerconfig : playerInputControllers) {
         * playerconfig.validatePlayer();
         * Player player = playerconfig.getPlayer();
         * }
         * try {
         * Parent root = App.loadFXML("components/Grid");
         * Scene rootScene = ((Node) event.getSource()).getScene();
         * rootScene.setRoot(root);
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         */

    }

    // TODO research onChange update handlders for Text & Combobox
}
