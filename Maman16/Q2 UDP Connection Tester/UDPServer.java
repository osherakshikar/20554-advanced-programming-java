import java.io.IOException;
import java.net.*;

/**
 * A simple UDP echo server that receives messages from clients and echoes them back.
 * The server listens to port 8888 and runs indefinitely until manually stopped.
 */
public class UDPServer {

    /**
     * Main method that starts the UDP echo server.
     * Creates a DatagramSocket listening on port 8888 and processes incoming packets
     * by echoing them back to the sender.
     *
     * @param args Command line arguments (not used)
     * @throws RuntimeException if an IOException occurs during socket operations
     */
    public static void main(String[] args) {

        DatagramSocket serverSocket = null;
        try{
            // Create server socket bound to port 8888
            serverSocket = new DatagramSocket(8888);
            byte[] buffer = new byte[1024]; // Buffer to hold incoming data (max 1024 bytes)

            System.out.println("Server is running and waiting for messages...");

            // Main server loop - runs indefinitely
            while (true) {
                // Create packet to receive incoming data
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet); // Blocking call - wait for client message

                // Process the received packet - echo it back to sender
                InetAddress clientAddress = packet.getAddress(); // Get client's IP address
                int clientPort = packet.getPort(); // Get client's port number

                // Create response packet with same data, sent back to client
                DatagramPacket response = new DatagramPacket(packet.getData(), packet.getLength(), clientAddress, clientPort);
                serverSocket.send(response); // Send echo response back to client
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            // Cleanup: close socket if it was created and is still open
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        }
    }
}