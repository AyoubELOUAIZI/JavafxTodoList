package com.example.todolistsamebase.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.example.todolistsamebase.model.Todo;

import java.util.Random;

public class TodoController {

    @FXML
    private ListView<Todo> todoListView;

    @FXML
    private TextField titleField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Label statusLabel;

    private final ObservableList<Todo> todoList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Initialize the todoListView with the todoList
        todoListView.setItems(todoList);
    }

    private final Random random = new Random();

    @FXML
    private void addTodo() {
        // Get values from text fields
        String title = titleField.getText();
        String description = descriptionField.getText();

        // Validate input
        if (title.isEmpty() || description.isEmpty()) {
            statusLabel.setText("Please enter both title and description.");
            return;
        }


        // Generate a random id
        int randomId = generateRandomId();


        // Create a new Todo object and add it to the list
        Todo newTodo = new Todo(randomId, title, description);

        todoList.add(newTodo);

        // Clear text fields
        titleField.clear();
        descriptionField.clear();
        statusLabel.setText("Todo added successfully.");
    }

    private int generateRandomId() {
        return random.nextInt(1000); // Adjust the range as needed
    }

    @FXML
    private void deleteTodo() {
        // Get the selected todo from the list
        Todo selectedTodo = todoListView.getSelectionModel().getSelectedItem();

        // Check if a todo is selected
        if (selectedTodo != null) {
            // Remove the selected todo from the list
            todoList.remove(selectedTodo);
            statusLabel.setText("Todo deleted successfully.");
        } else {
            statusLabel.setText("Please select a todo to delete.");
        }
    }
}
