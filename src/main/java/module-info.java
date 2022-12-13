module ch.uzh {
    requires javafx.controls;
    requires javafx.fxml;

    opens ch.uzh.controller to javafx.fxml;
    exports ch.uzh;
}
