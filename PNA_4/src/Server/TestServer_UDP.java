package Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public class TestServer_UDP {

    public final static int DEFAULT_PORT = 8001; // определение порт сервера
    public double x, y;
    public int state;
    public boolean stopFlag = false;
    public void runServer() throws IOException {
        DatagramSocket socket = null;
        String send_message;
        try {
            socket = new DatagramSocket(DEFAULT_PORT);
            System.out.println("UDPServer-> Started on " + socket.getLocalAddress() + ":" + socket.getLocalPort());
            while(!stopFlag)
            {
                byte[] get_message = new byte[512];
                DatagramPacket recvPacket = new DatagramPacket(get_message, 512);
                socket.receive(recvPacket);
                get_message = recvPacket.getData();
                System.out.println("UDPServer-> Message: " + get_message.toString());
                send_message = new String(get_message).trim();
                DatagramPacket sendPacket = new DatagramPacket(send_message.getBytes(), send_message.length(), recvPacket.getAddress(), recvPacket.getPort());
                socket.send(sendPacket);
            }
            System.out.println("UDPServer-> Stopped");
        }
        catch(SocketException exc)
        {
            exc.printStackTrace();
        }
        finally {
            if (socket != null) {
                socket.close();//закрытие сокета сервера
            }
        }
    }

    public static void main(String[] args) {//метод main
        try {
            TestServer_UDP udpSvr = new TestServer_UDP();//создание объекта udpSvr
            udpSvr.runServer();//вызов метода объекта runServer
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

