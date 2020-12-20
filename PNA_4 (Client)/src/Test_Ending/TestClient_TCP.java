package Test_Ending;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestClient_TCP
{
    static ExecutorService executeIt = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException, InterruptedException
    {
        int j = 0;

        while (j < 5)
        {
            j++;
            executeIt.execute(new ThreadClient(j));
            Thread.sleep(1000);
        }
    }
}
