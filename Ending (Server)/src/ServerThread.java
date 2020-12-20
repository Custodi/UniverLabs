import Database.DB_Interface;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable
{
    private Socket client = null;
    private final int id;

    public ServerThread(Socket _client, int id_thread)
    {
        client = _client;
        id = id_thread;
    }

    @Override
    public void run()
    {
        System.out.println("Thread connection " + id + " with client established.");
        MT_Server.current_threads++;
        DataInputStream input_stream = null; // Канал чтения из сокета
        DataOutputStream output_stream = null; // Канал записи в сокет

        String client_query;
        try
        {
            output_stream = new DataOutputStream(client.getOutputStream());
            input_stream = new DataInputStream(client.getInputStream());

            while(true)
            {
                client_query = input_stream.readUTF();
                if(client_query.equals("quite"))
                {
                    System.out.println("Disconnect thread " + id);
                    break;
                }
                else {
                    System.out.println("Query by client " + client.getInetAddress() + " ==> " + client_query);
                    String buff = DB_Interface.make_query(client_query);
                    if(buff.isEmpty())
                    {
                        buff = "None";
                    }
                    System.out.println("Answer by server to client " + client.getInetAddress() + " ==> " + buff);
                    output_stream.writeUTF(buff);
                }
            }

        }
        catch (EOFException t)
        {
            System.out.println("Disconnect thread " + id + " initialized by client " + client.getInetAddress());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                input_stream.close();
                output_stream.close();
                client.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            MT_Server.current_threads--;
        }
    }
}
