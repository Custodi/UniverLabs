package Test_Ending;

import Server.Server_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable {

    private Socket client;

    public ServerThread(Socket _client)
    {
        client = _client;
    }

    @Override
    public void run()
    {
        TestServer_TCP.count_threads++;

        try
        {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());

            while (!client.isClosed())
            {
                String entry = in.readUTF();

                System.out.println("Message by client: " + entry);
                if (entry.equalsIgnoreCase("quit"))
                {
                    Thread.sleep(3000);
                    System.out.println("Destroy a thread");
                    break;
                }
            }
            System.out.println("Client disconnected");

            in.close();
            out.close();

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally
        {
            TestServer_TCP.count_threads--;
        }
    }
}