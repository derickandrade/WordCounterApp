module com.tp2.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.tp2.app to javafx.fxml;
    exports com.tp2.app;
}