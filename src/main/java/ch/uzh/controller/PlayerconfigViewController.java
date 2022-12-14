package ch.uzh.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ch.uzh.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerconfigViewController implements Initializable{

    @FXML
    private VBox vboxPlayerConfigList;

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
        vboxPlayerConfigList.getChildren().add(child);
        
    }

    @FXML
    public void goNext() {
        System.out.println("next");
    }

    // TODO research onChange update handlders for Text & Combobox
}
