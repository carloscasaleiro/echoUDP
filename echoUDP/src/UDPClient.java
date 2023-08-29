import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * UDPClient that sends packets to a specific UDP host server and receive response
 */
public class UDPClient {

    private static final String hostName = "127.0.0.1";
    private String message;
    private DatagramSocket clientSocket;

    public static void main(String[] args) {
        UDPClient udpClient = new UDPClient();
        udpClient.start();
    }

    public void start() {

        try {
            getMessage();
            sendData();
            receiveData();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void getMessage() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter Message: ");
        message = in.readLine();

        in.close();
    }

    private void sendData() throws IOException {

        clientSocket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(hostName);

        byte[] sendData = message.getBytes(StandardCharsets.UTF_8);

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, UDPServer.PORT);
        clientSocket.send(sendPacket);
    }

    private void receiveData() throws IOException {

        byte[] receiveData = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        System.out.println("Waiting for return packet");
        clientSocket.setSoTimeout(10000);

        clientSocket.receive(receivePacket);
        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);

        System.out.println("Received Message: " + receivedMessage);

        clientSocket.close();
    }
}
