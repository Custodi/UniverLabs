package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class TestClient_UDP {
    public void runClient() throws IOException {
        DatagramSocket socket = null;
        String send_message;
        try {
            socket = new DatagramSocket();
            System.out.println("UDPClient -> Started\n");
            Scanner input = new Scanner(System.in);
            while(true)
            {
                byte[] get_message = new byte[512];
                System.out.println("Enter command to server: ");
                send_message = input.nextLine();
                DatagramPacket sendPacket = new DatagramPacket(send_message.getBytes(), send_message.length(), InetAddress.getByName("127.0.0.1"), 8001);//создание
                socket.send(sendPacket);
                DatagramPacket recvPacket = new DatagramPacket(get_message, 512);
                socket.receive(recvPacket);
                send_message = new String(recvPacket.getData()).trim();
                System.out.println("~Server Answer~: " + send_message);
                if (send_message.equals("Disconnect")) break;
            }
            System.out.println("Disconnecting...");
            input.close();
        }
        finally
        {
            if (socket != null)
            {
                socket.close();
            }
        }
    }
    public static void main(String[] args) {
        try {
            TestClient_UDP client = new TestClient_UDP();
            client.runClient();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

