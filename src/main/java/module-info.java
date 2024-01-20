module com.example.todolistsamebase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.todolistsamebase to javafx.fxml;
    exports com.example.todolistsamebase;
    exports com.example.todolistsamebase.controller;
    opens com.example.todolistsamebase.controller to javafx.fxml;
}