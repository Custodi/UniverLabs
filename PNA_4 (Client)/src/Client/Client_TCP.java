package Client;

import java.io.*;
import java.net.*;

public class Client_TCP
{
    public static void main(String[] args)
    {
        try {
            System.out.println("Server connecting....");
            Socket clientSocket = new Socket("127.0.0.1",129);
            System.out.println("Connection established....");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream out_stream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in_stream = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Enter summary to send to server ['quite' - close connection with server]:");
            String clientMessage = stdin.readLine();
            System.out.println("You have entered: " + clientMessage + "\n");
            while(!clientMessage.equals("quite"))
            {
                out_stream.writeObject(clientMessage);
                System.out.println("~Server Answer~\n" + in_stream.readObject());
                System.out.println("---------------------------");
                clientMessage = stdin.readLine();
                System.out.println("You have entered: " + clientMessage);
            }
            out_stream.close();
            in_stream.close();
            clientSocket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}