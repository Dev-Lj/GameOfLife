package ch.uzh.controller.components;

import java.net.URL;
import java.util.ResourceBundle;
import ch.uzh.model.lobby.Player;
import ch.uzh.model.lobby.PlayerConfig;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.collections.ObservableList;

public class PlayerconfigController_old implements Initializable {
    private Player player = new Player();
    private Label lab = new Label();
    private TextField txt = new TextField();
    private ComboBox<String> comBox;

    public PlayerconfigController_old(int i) {
        lab.setText("Player " + i);
        comBox = new ComboBox<String>();
    }

    ObservableList<String> data = FXCollections.observableArrayList(
            PlayerConfig.COLORS);

    static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(20, 20);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            }
        }
    }

    private ComboBox<String> createComboBox() {
        comBox.setItems(data);
        Callback<ListView<String>, ListCell<String>> factory = new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new ColorRectCell();
            }
        };
        comBox.setCellFactory(factory);
        comBox.setButtonCell(factory.call(null));
        return comBox;
    }

    @FXML
    public HBox getField() {
        ComboBox<String> combBox = createComboBox();
        HBox hbox = new HBox(lab, combBox, txt);
        hbox.setSpacing(20);
        return hbox;
    }

    @FXML
    public void updatePlayer() {
        player.setName(txt.getText());
        try {
            player.setColor(comBox.getValue());
        } catch (Exception e) {
            player.setColor(null);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Player getPlayer() {
        return player;
    }

}
