package Client;

import java.io.*;
import java.net.*;

public class My_TCP_Client
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Server connecting....");
            Socket clientSocket = new Socket("127.0.0.1",129);
            System.out.println("Connection established....");

            ObjectOutputStream out_stream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in_stream = new ObjectInputStream(clientSocket.getInputStream());

            out_stream.writeObject("Dsadas");
            out_stream.close();
            in_stream.close();
            clientSocket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
