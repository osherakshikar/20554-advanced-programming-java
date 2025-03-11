package com.example.chatapp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Main server class for the chat application.
 * Listens for client connections and creates handler threads for each connected client.
 */
public class Server {
    /**
     * The port number on which the server will listen
     */
    private final static int PORT = 9090;

    /**
     * Thread-safe set that stores all connected client handlers.
     * Using ConcurrentHashMap.newKeySet() ensures thread safety for client management.
     */
    private final static Set<ServerThread> clients = ConcurrentHashMap.newKeySet();

    /**
     * Main method to start the chat server.
     * Creates a server socket and continuously accepts new client connections.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Initialize the server socket on the specified port
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Chat Server started successfully on port " + PORT);
            System.out.println("Server IP: " + InetAddress.getLocalHost().getHostAddress());

            // Infinite loop to keep accepting new client connections
            while (true) {
                // Wait for a new client connection (blocking call)
                System.out.println("Waiting for client connections...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Create a new client handler for the connected client
                ServerThread handler = new ServerThread(clientSocket, clients);

                // Add the new client handler to the shared set of clients
                clients.add(handler);

                // Create and start a new thread for handling this client
                Thread t = new Thread(handler);
                // Set as daemon thread so it won't prevent JVM shutdown
                t.setDaemon(true);
                // Start the thread to begin processing client messages
                t.start();
            }
        } catch (IOException e) {
            // Log server startup/runtime errors
            System.err.println("Fatal server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}