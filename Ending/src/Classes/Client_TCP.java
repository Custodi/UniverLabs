package Classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import DB_Classes.Staff;

public class Client_TCP implements Runnable
{
    Socket socket;
    DataOutputStream output_stream;
    DataInputStream input_stream;
    String query, answer;
    public Staff registed_employee = null;

    @Override
    public synchronized void run()
    {
        try
        {
            socket = new Socket("localhost", 129);
            output_stream = new DataOutputStream(socket.getOutputStream());
            input_stream = new DataInputStream(socket.getInputStream());
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        while(!socket.isClosed())
        {
            try
            {
                wait();
                if(socket.isClosed()) break;
                output_stream.writeUTF(query);
                answer = input_stream.readUTF();
                notify();

            } catch (InterruptedException | IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    public synchronized String send_query(String query)
    {
        try
        {
            this.query = query;
            notify();
            wait();
            return answer;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public synchronized void close_connection()
    {
        try
        {
            input_stream.close();
            output_stream.close();
            socket.close();
            notify();
        }
        catch(IOException exc)
        {
            exc.printStackTrace();
        }
    }
}
