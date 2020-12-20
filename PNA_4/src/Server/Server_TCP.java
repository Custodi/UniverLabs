package Server;
import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server_TCP
{
    public static void main(String[] args)
    {
        String mes;

        ServerSocket server_socket = null;
        Socket accepted_client = null;
        ObjectInputStream input_stream = null;
        ObjectOutputStream output_stream = null;

        try
        {
            server_socket = new ServerSocket(129);
            System.out.println("Server activated");

            accepted_client = server_socket.accept();
            System.out.println("Connection is established");

            input_stream = new ObjectInputStream(accepted_client.getInputStream());
            output_stream = new ObjectOutputStream(accepted_client.getOutputStream());

            String message = (String)input_stream.readObject();
            System.out.println(message);

        }
        catch(Exception exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        finally
        {
            try
            {
                input_stream.close();
                output_stream.close();
                accepted_client.close();
                server_socket.close();
            }
            catch(Exception exc)
            {
                
            }
        }
    }
}
