import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * UDPServer class that listens for UDP packets and retransmits them back to the sender in Uppercase
 */
public class UDPServer {

    public static final int PORT = 8000;

    public static void main(String[] args) {
        UDPServer udpServer = new UDPServer();
        udpServer.start();
    }

    public void start() {

        try {

            DatagramSocket serverSocket = new DatagramSocket(PORT);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            System.out.println("Waiting for datagram packet");
            serverSocket.receive(receivePacket);

            String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
            System.out.println("Message: " + message);

            byte[] sendData = message.toUpperCase().getBytes(StandardCharsets.UTF_8);

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(sendPacket);
            serverSocket.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
