module kalkulator {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens kalkulator to javafx.fxml;
    exports kalkulator;
}
