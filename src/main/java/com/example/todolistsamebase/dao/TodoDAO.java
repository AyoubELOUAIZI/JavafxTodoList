package com.example.todolistsamebase.dao;

import com.example.todolistsamebase.database.DatabaseConnector;
import com.example.todolistsamebase.model.Todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    private static final String SELECT_ALL_TODOS = "SELECT * FROM todos";
    private static final String INSERT_TODO = "INSERT INTO todos (title, description, due_date) VALUES (?, ?, ?)";
    // Add more SQL queries for update and delete operations as needed

    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");

                Todo todo = new Todo(id, title, description);
                todos.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todos;
    }

    public void addTodo(Todo todo) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getDescription());
           // preparedStatement.setDate(3, new Date(todo.getDueDate().getTime()));

            preparedStatement.executeUpdate();

            // Retrieve the generated ID if needed
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    todo.setId(generatedId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add methods for update and delete operations as needed
}
