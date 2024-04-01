module co.edu.uniquindio.pf3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.pf3 to javafx.fxml;
    exports co.edu.uniquindio.pf3;
}