module master.iitn {
    requires javafx.controls;
    requires javafx.fxml;

    opens master.iitn to javafx.fxml;
    exports master.iitn;
}
