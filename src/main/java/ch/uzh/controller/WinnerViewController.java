package ch.uzh.controller;



import ch.uzh.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class WinnerViewController {
    @FXML private Label lblWinnerName;
    @FXML private Label lblErrMsg;
    
    public void setWinnerName(String winnerName) {
        lblWinnerName.setText(winnerName);
    }
    
    @FXML
    public void restartGame(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PlayerconfigView.fxml"));
            Parent p = fxmlLoader.load();
            Scene rootScene = ((Node) event.getSource()).getScene();
            rootScene.setRoot(p);
        } catch (Exception e) {
            e.printStackTrace();
            lblErrMsg.setText(e.getMessage());
        }

    }
}
