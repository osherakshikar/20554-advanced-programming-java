package com.example.chatapp;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Manages the network communication for the chat client.
 * Handles connecting to the server, sending messages, and receiving messages.
 */
public class ChatClientThread {
    private static final int PORT = 9090;

    // Network components
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    // Reference to controller
    private ChatClientController ChatClientController;

    /**
     * Constructs a new ChatClientThread with the specified username and controller.
     *
     * @param username   The username of the client
     * @param chatClient The controller that will process incoming messages
     */
    public ChatClientThread(String username, ChatClientController chatClient) {
        this.username = username;
        this.ChatClientController = chatClient;
    }

    /**
     * Attempts to establish a connection to the chat server.
     *
     * @param serverAddress The IP address or hostname of the server
     * @return true if connection was successful, false otherwise
     */
    public boolean connect(String serverAddress) {
        if (serverAddress.isEmpty()) {
            serverAddress = "localhost";
        }

        try {
            // Use localhost IP explicitly if needed
            if (serverAddress.equals("localhost")) {
                serverAddress = "127.0.0.1";
            }

            socket = new Socket();
            // Set a connection timeout of 3 seconds to avoid hanging indefinitely
            socket.connect(new InetSocketAddress(serverAddress, PORT), 3000);

            // Initialize I/O streams
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send username to server for registration
            out.println(username);

            // Start a background thread to listen for incoming messages
            Thread listenerThread = new Thread(this::listenForMessages);
            listenerThread.setDaemon(true); // Make it a daemon thread so it doesn't prevent app exit
            listenerThread.start();

            return true;

        } catch (IOException e) {
            // Connection failed
            return false;
        }
    }

    /**
     * Disconnects from the server by sending an exit message and closing the socket.
     */
    public void disconnect() {
        if (socket != null && !socket.isClosed()) {
            try {
                if (out != null) {
                    // Notify server that client is disconnecting
                    out.println("EXIT");
                }
                socket.close();
            } catch (IOException e) {
                // Ignore closing errors
            }
        }
    }

    /**
     * Sends a message to the server.
     *
     * @param message The message to send
     */
    public void sendMessage(String message) {
        if (!message.isEmpty() && out != null) {
            out.println(message);
        }
    }

    /**
     * Listens for incoming messages from the server in a continuous loop.
     * When messages are received, they are dispatched to the controller on the JavaFX application thread.
     */
    private void listenForMessages() {
        try {
            String message;
            // Keep reading messages until the stream ends or an exception occurs
            while ((message = in.readLine()) != null) {
                final String msg = message;
                // Process the message on the JavaFX application thread
                Platform.runLater(() -> ChatClientController.processMessage(msg));
            }
        } catch (IOException e) {
            // Check if the socket was intentionally closed
            if (socket != null && !socket.isClosed()) {
                // Notify the UI that the server disconnected unexpectedly
                Platform.runLater(() -> ChatClientController.handleServerDisconnection());
            }
        }
    }
}