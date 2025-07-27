import java.net.*;
import java.util.concurrent.*;

/**
 * A UDP client that demonstrates concurrent sending and receiving of messages.
 * The client sends numbered messages to a server and simultaneously listens for responses.
 */
public class Client {

    /**
     * Main method that creates an UDP client with concurrent sender and receiver threads.
     *
     * @param args Command line arguments where args[0] should be the server hostname
     * @throws Exception if network operations fail
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java Client <server-hostname>");
            return;
        }

        String serverHost = args[0];
        InetAddress serverAddress = InetAddress.getByName(serverHost);
        int port = 8888; // Default server port

        // Create UDP socket with 10 second timeout
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(10000); // 10 second timeout for receive operations

        // Create thread pool for concurrent sender and receiver
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Sender thread - sends numbered messages 1-10 to the server
        executor.execute(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String msg = Integer.toString(i);
                    byte[] data = msg.getBytes();
                    DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);
                    socket.send(packet);
                    Thread.sleep(100); // 100ms delay between sends
                }
            } catch (Exception e) {
                System.err.println("Sender error: " + e);
            }
        });

        // Receiver thread - listens for up to 10 response messages
        executor.execute(() -> {
            int received = 0;
            long startTime;
            for (int i = 0; i < 10; i++) {
                byte[] buffer = new byte[1024]; // Buffer for incoming data
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    startTime = System.currentTimeMillis();
                    socket.receive(packet); // Blocking receive with timeout
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Received from " + packet.getAddress().getHostName() + ": " + msg);
                    received++;
                } catch (SocketTimeoutException e) {
                    System.out.println("Timeout waiting for message " + (i + 1));
                    break;
                } catch (Exception e) {
                    System.err.println("Receiver error: " + e);
                    break;
                }
            }
            System.out.println("Total messages received: " + received);
        });

        // Shutdown the executor service
        executor.shutdown();
    }
}