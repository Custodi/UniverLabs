import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MT_Server
{
    static int current_threads = 0;
    public static void main(String[] args)
    {
        int MAX_THREADS;
        try
        {
            FileReader reader = new FileReader("setting.cfg");
            int c;
            String str = "";
            while((c=reader.read())!=-1) {
                str += (char) c;
            }
            MAX_THREADS = Integer.parseInt(str);
            System.out.println("Max threads are limited by " + MAX_THREADS);
        }
        catch (Exception e)
        {
            System.out.println("Error of config file, max threads are limited by 2");
            MAX_THREADS = 2;
        }
        ExecutorService executeIt = Executors.newFixedThreadPool(MAX_THREADS);
        ServerSocket server_socket = null;
        Socket accepted_client = null;
        try
        {
            System.out.println("Server is launched");
            server_socket = new ServerSocket(129); // Сокет сервера
            while (true)
            {
                accepted_client = server_socket.accept();
                executeIt.execute(new ServerThread(accepted_client, current_threads));
            }
        }
        catch(Exception exc)
        {
            System.out.println("Error: " + exc.getMessage());
            try
            {
                accepted_client.close();
                server_socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }
}
