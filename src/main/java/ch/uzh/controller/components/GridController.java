package ch.uzh.controller.components;

import java.net.URL;
import java.util.ResourceBundle;

import ch.uzh.model.grid.Grid;
import ch.uzh.model.grid.GridObserver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class GridController implements GridObserver, Initializable{

    private Grid grid = new Grid();
    @FXML
    private Label lblChange;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grid.attachObserver(this);
    }

    @Override
    public void notifyUpdate() {
        System.out.println("Grid was changed");
        lblChange.setText("Grid was changed");

    }

    @FXML
    public void changeGrid(){
        System.out.print("Btn clicked");
        grid.changeGrid();
    }
    
}
