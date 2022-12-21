module ch.uzh {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens ch.uzh.controller to javafx.fxml;
    opens ch.uzh.controller.components to javafx.fxml;

    exports ch.uzh;
}
