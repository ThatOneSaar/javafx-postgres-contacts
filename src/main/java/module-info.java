module com.example.javafx_oracle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.javafx_oracle to javafx.fxml;
    exports com.example.javafx_oracle;
    exports com.example.javafx_oracle.presentation;
    opens com.example.javafx_oracle.presentation to javafx.fxml;
}