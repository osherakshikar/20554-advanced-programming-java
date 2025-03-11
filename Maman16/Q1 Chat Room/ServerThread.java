package com.example.chatapp;

import java.io.*;
import java.net.Socket;
import java.util.Set;

/**
 * Handles an individual client connection to the chat server.
 * Manages receiving messages from the client and broadcasting messages to all connected clients.
 * Implements Runnable to allow each client to be handled in a separate thread.
 */
public class ServerThread implements Runnable {

    /** Socket connection to the client */
    private Socket socket;
    /** Output stream to send messages to the client */
    private PrintWriter out;
    /** Input stream to receive messages from the client */
    private BufferedReader in;
    /** Username of the connected client */
    private String username;
    /** Shared reference to the set of all connected clients */
    private Set<ServerThread> clients;

    /**
     * Creates a new thread handler for a client connection.
     *
     * @param socket The socket connection to the client
     * @param clients The shared set of all connected client handlers
     */
    public ServerThread(Socket socket, Set<ServerThread> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    /**
     * Main processing method that runs in a separate thread.
     * Handles client connection initialization, message processing, and disconnection.
     */
    @Override
    public void run() {
        try {
            // Initialize I/O streams
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // First message from client should be the username
            username = in.readLine();
            System.out.println("New user joined: " + username);

            // Send current user list to the new client
            StringBuilder userList = new StringBuilder("USERLIST:");
            for (ServerThread client : clients) {
                // Skip self and clients without usernames (not fully initialized)
                if (client != this && client.username != null) {
                    userList.append(client.username).append(",");
                }
            }
            out.println(userList.toString());

            // Notify all clients that a new user has joined
            broadcast("JOIN:" + username);

            // Message processing loop
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // If client sends "EXIT", break the loop
                if (inputLine.equals("EXIT")) {
                    break;
                }
                // Format and broadcast the message to all clients
                broadcast("MSG:" + username + ":" + inputLine);
            }

            // Handle normal client disconnection
            clients.remove(this);
            broadcast("LEAVE:" + username);
            System.out.println("User left: " + username);
            socket.close();

        } catch (Exception e) {
            // Handle unexpected errors
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            // Cleanup resources even if an exception occurred
            if (username != null) {
                clients.remove(this);
                broadcast("LEAVE:" + username);
            }
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null && !socket.isClosed()) socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }

    /**
     * Broadcasts a message to all connected clients.
     * The message will be sent as-is, so it should be properly formatted before calling this method.
     *
     * @param message The message to broadcast to all clients
     */
    public void broadcast(String message) {
        for (ServerThread client : clients) {
            try {
                // Send the message to each client
                client.out.println(message);
            } catch (Exception e) {
                // Continue broadcasting to other clients even if one fails
                e.printStackTrace();
            }
        }
    }
}