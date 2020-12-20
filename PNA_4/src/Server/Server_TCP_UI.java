package Server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_TCP_UI
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

            String message;
            message = (String)input_stream.readObject();
            while(message != "quite")
            {
                    int l = message.length();
                    char[] byte_mes = new char[l];
                    message.getChars(0, l, byte_mes,0);
                    for(int i = 0; i < l/2; i++)
                    {
                        char buff;
                        buff = byte_mes[i];
                        byte_mes[i] = byte_mes[l-i-1];
                        byte_mes[l-i-1] = buff;
                    }
                    message = new String(byte_mes);
                    output_stream.writeObject(message);
                    message = (String)input_stream.readObject();
            }


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

