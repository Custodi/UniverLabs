package Test_Ending;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestServer_TCP
{
    static int count_threads = 0;
    static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    public static void main(String[] args)
    {
        ServerSocket server_socket = null;
        Socket accepted_client = null;

        try
        {
            server_socket = new ServerSocket(129);
            System.out.println("Server activated");

            while(true)
            {
                accepted_client = server_socket.accept();
                System.out.println("Connection is established");
                executeIt.execute(new ServerThread(accepted_client));
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
                accepted_client.close();
                server_socket.close();
            }
            catch(Exception exc)
            {

            }
        }
    }

}