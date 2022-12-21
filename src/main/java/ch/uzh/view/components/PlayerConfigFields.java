package ch.uzh.view.components;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.scene.paint.Color;

public class PlayerConfigFields {
    ObservableList<String> colorData;
    ComboBox<String> comBox;
    Label lab;
    TextField txt;

    public PlayerConfigFields(List<String> colors, String labelText) {
        this.colorData = FXCollections.observableArrayList(colors);
        this.comBox = new ComboBox<String>();
        this.lab = new Label(labelText);
        lab.setMinWidth(60);
        this.txt = new TextField();
        this.txt.setPromptText("Name");
    }

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
        comBox.setItems(colorData);
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
    public HBox getItem() {
        ComboBox<String> combBox = createComboBox();
        HBox hbox = new HBox(lab, combBox, txt);
        hbox.setSpacing(20);
        return hbox;
    }

    public String getName() {
        return txt.getText();
    }

    public String getColor() {
        return comBox.getValue();
    }

}
