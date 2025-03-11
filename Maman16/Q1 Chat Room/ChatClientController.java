package com.example.chatapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Controller class for the Chat Client application.
 * Handles UI interactions and orchestrates communication with the server
 * through the ChatClientThread.
 */
public class ChatClientController {

    @FXML
    private TextArea chatArea;

    @FXML
    private Button connectButton;

    @FXML
    private Button disconnectButton;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    @FXML
    private TextField serverField;

    @FXML
    private Label statusLabel;

    @FXML
    private ListView<String> userListView;

    private String username;
    private ChatClientThread networkClient;

    /**
     * Initializes the controller.
     * Sets up initial UI state and configures window closing behavior.
     */
    public void initialize() {
        // Set initial state
        chatArea.setEditable(false);
        chatArea.setWrapText(true);
        disconnectButton.setDisable(true);
        messageField.setDisable(true);
        sendButton.setDisable(true);

        // Make sure the window closing event also disconnects the client
        Platform.runLater(() -> {
            Stage stage = (Stage) connectButton.getScene().getWindow();
            stage.setOnCloseRequest(e -> handleDisconnectWithoutEvent());
        });
    }

    /**
     * Handles the connect button action by showing the login dialog.
     *
     * @param event The action event triggered by the connect button
     */
    @FXML
    void handleConnect(ActionEvent event) {
        showLoginDialog();
    }

    /**
     * Handles the disconnect button action.
     *
     * @param event The action event triggered by the disconnect button
     */
    @FXML
    void handleDisconnect(ActionEvent event) {
        disconnect();
    }

    /**
     * Handles the send message button action.
     * Sends the message via the network client if available.
     *
     * @param event The action event triggered by the send button
     */
    @FXML
    void handleSendMessage(ActionEvent event) {
        String message = messageField.getText();
        if (!message.trim().isEmpty() && networkClient != null) {
            networkClient.sendMessage(message);
            messageField.clear();
            messageField.requestFocus();
        }
    }

    /**
     * Shows a dialog for the user to enter their username.
     * If a valid username is provided, attempts to connect to the server.
     */
    public void showLoginDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Chat Login");
        dialog.setHeaderText("Enter your username:");
        dialog.setContentText("Username:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                username = name.trim();
                connect();
            }
        });
    }

    /**
     * Attempts to connect to the chat server using the network client.
     * Updates the UI based on the connection result.
     */
    public void connect() {
        String serverAddress = serverField.getText();
        statusLabel.setText("Connecting to " + serverAddress + "...");

        // Create a new network client
        networkClient = new ChatClientThread(username, this);

        // Try to connect
        boolean connected = networkClient.connect(serverAddress);

        if (connected) {
            // Update UI on successful connection
            connectButton.setDisable(true);
            disconnectButton.setDisable(false);
            messageField.setDisable(false);
            sendButton.setDisable(false);
            statusLabel.setText("Connected as: " + username);
            messageField.requestFocus();
        } else {
            statusLabel.setText("Failed to connect to " + serverAddress);
            // Show an error dialog
            showAlert("Connection Error", "Could not connect to server");
        }
    }

    /**
     * Handles server disconnection events.
     * Updates the UI and disconnects the client.
     */
    public void handleServerDisconnection() {
        statusLabel.setText("Disconnected from server");
        disconnect();
    }

    /**
     * Disconnects from the server and updates the UI accordingly.
     */
    public void disconnect() {
        if (networkClient != null) {
            networkClient.disconnect();
        }
        connectButton.setDisable(false);
        disconnectButton.setDisable(true);
        messageField.setDisable(true);
        sendButton.setDisable(true);
        statusLabel.setText("Disconnected");
        userListView.getItems().clear();
    }

    /**
     * Processes messages received from the server.
     * Handles different message types such as user list updates, join/leave notifications,
     * and chat messages.
     *
     * @param message The message received from the server
     */
    public void processMessage(String message) {
        if (message.startsWith("USERLIST:")) {
            // Handle user list
            String[] users = message.substring(9).split(",");
            userListView.getItems().clear();
            for (String user : users) {
                if (!user.isEmpty()) {
                    userListView.getItems().add(user);
                }
            }
            userListView.getItems().add(username + " (you)");
        } else if (message.startsWith("JOIN:")) {
            // Handle user join
            String user = message.substring(5);
            chatArea.appendText("*** " + user + " has joined the chat ***\n");
            if (!userListView.getItems().contains(user) &&
                    !userListView.getItems().contains(user + " (you)")) {
                userListView.getItems().add(user);
            }

        } else if (message.startsWith("LEAVE:")) {
            // Handle user leave
            String user = message.substring(6);
            chatArea.appendText("*** " + user + " has left the chat ***\n");
            userListView.getItems().remove(user);

        } else if (message.startsWith("MSG:")) {
            // Handle chat message
            String[] parts = message.substring(4).split(":", 2);
            if (parts.length == 2) {
                chatArea.appendText(parts[0] + ": " + parts[1] + "\n");
            }
        }
    }

    /**
     * Shows an error alert dialog with the specified title and message.
     *
     * @param title   The title of the alert dialog
     * @param message The message to display in the alert dialog
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Handles disconnection when no event is available.
     * Used for window close events.
     */
    private void handleDisconnectWithoutEvent() {
        handleDisconnect(new ActionEvent());
    }
}