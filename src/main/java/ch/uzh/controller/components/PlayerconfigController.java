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

public class PlayerconfigController implements Initializable {
    private Player player = new Player();
    private Label lab = new Label();
    private TextField txt = new TextField();
    private ComboBox<String> comBox;

    public PlayerconfigController(int i) {
        lab.setText("Player " + i);
        comBox = createComboBox();
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
        ComboBox<String> combnew = new ComboBox<String>();
        combnew.setItems(data);
        Callback<ListView<String>, ListCell<String>> factory = new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new ColorRectCell();
            }
        };
        combnew.setCellFactory(factory);
        combnew.setButtonCell(factory.call(null));
        return combnew;
    }

    @FXML
    public HBox getField() {
        ComboBox<String> combBox = createComboBox();
        HBox hbox = new HBox(lab, combBox, txt);
        hbox.setSpacing(20);
        return hbox;
    }

    public void updatePlayer() {
        player.setName(txt.getText());
        System.out.println(comBox.getValue().toString());
        try {
            player.setColor(comBox.getValue().toString());
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
