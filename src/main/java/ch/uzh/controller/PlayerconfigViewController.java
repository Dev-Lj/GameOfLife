package ch.uzh.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ch.uzh.App;
import ch.uzh.controller.components.PlayerconfigController;
import ch.uzh.model.lobby.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerconfigViewController implements Initializable{

    @FXML
    private VBox vboxPlayerConfigList;
    private List<PlayerconfigController> playerInputControllers = new ArrayList<PlayerconfigController>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("components/Playerconfig.fxml"));
        Node child;
        try {
            child = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            child = new Label("Failed to load component");
        }
        playerInputControllers.add(loader.getController());
        vboxPlayerConfigList.getChildren().add(child);
        
    }

    @FXML
    public void goNext(ActionEvent event) {
        for (PlayerconfigController playerconfig : playerInputControllers) {
            playerconfig.validatePlayer();
            Player player = playerconfig.getPlayer();
        }
        try {
            Parent root = App.loadFXML("components/Grid");
            Scene rootScene = ((Node)event.getSource()).getScene();
            rootScene.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    // TODO research onChange update handlders for Text & Combobox
}
