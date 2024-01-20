module com.example.todolistsamebase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.todolistsamebase to javafx.fxml;
    exports com.example.todolistsamebase;
    exports com.example.todolistsamebase.controller;
    opens com.example.todolistsamebase.controller to javafx.fxml;
}