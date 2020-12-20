package Server;
import java.io.*;
import java.net.*;

public class Server_UDP {

    public final static int DEFAULT_PORT = 8001;
    public double x, y;
    public int state;
    public boolean stopFlag = false;
    public void runServer() throws IOException {
        DatagramSocket socket = null;
        String message;
        try {
            socket = new DatagramSocket(DEFAULT_PORT);
            System.out.println("UDPServer -> Started on " + socket.getLocalAddress() + ":" + socket.getLocalPort());
            while(!stopFlag)
            {
                byte[] message_from_client = new byte[512];
                DatagramPacket recvPacket = new DatagramPacket(message_from_client, 512);
                socket.receive(recvPacket);

                message_from_client = recvPacket.getData();
                message = new String(message_from_client).trim();
                System.out.println("Client " + recvPacket.getAddress() + " to Server: " + message);
                message = main_process(message);

                DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), recvPacket.getAddress(), recvPacket.getPort());
                socket.send(sendPacket);
                System.out.println("Server to Client " + recvPacket.getAddress() + ": " + message);
            }
            System.out.println("UDPServer -> Stopped");
        }
        finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public String main_process(String message)
    {
        // Проверка на получение значений переменных
        if(state != 0)
        {
            try
            {
                if(state == 1)
                {
                    x = Double.parseDouble(message);
                }
                else
                {
                    y = Double.parseDouble(message);
                }
                state = 0;
                return "Value was setted";
            }
            catch(NumberFormatException exc)
            {
                state = 0;
                return "Invalid value. Reset the command and try again.";
            }
        }
        switch(message)
        {
            case "SET X":
            {
                state = 1;
                return "Enter X value";
            }
            case "SET Y":
            {
                state = 2;
                return "Enter Y value";
            }
            case "GET RESULT":
            {
                double result;
                try
                {
                    result = 5 * Math.atan(this.x) - 0.25 * Math.cos
                            (this.x + 3 * Math.abs(this.x + this.y) + this.x * this.x/
                                    Math.pow(Math.abs(this.x + this.y * this.y), 3) + Math.pow(this.x, 3));
                    return Double.toString(result);
                }
                catch (Exception exc)
                {
                    return "Invalid values. Change values of expression and try again.";
                }
            }
            case "SAVE":
            {
                // Подсчёт значений
                double result;
                try
                {
                    result = 5 * Math.atan(this.x) - 0.25 * Math.cos
                            (this.x + 3 * Math.abs(this.x + this.y) + this.x * this.x/
                                    Math.pow(Math.abs(this.x + this.y * this.y), 3) + Math.pow(this.x, 3));
                }
                catch (Exception exc)
                {
                    return "Invalid values. Change values of expression and try again.";
                }
                // Формриование текстовой строки для записи
                String text = "X = " + this.x + "\nY = " + this.y + "\nResult: " + result;
                // Запись в файл
                try(FileOutputStream file = new FileOutputStream("C://Rez//Result.txt"))
                {
                    byte[] buffer = text.getBytes();
                    file.write(buffer, 0, buffer.length);
                }
                catch(Exception exc)
                {
                    return "Error of file recording.";
                }
                return "Data were written to file.";
            }
            case "QUIT":
            {
                this.stopFlag = true;
                return "Disconnect";
            }
            default:
            {
                return "Invalid command. Enter valid command.";
            }

        }
    }

    public static void main(String[] args) {
        try {
            Server_UDP udpSvr = new Server_UDP();
            udpSvr.runServer();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

