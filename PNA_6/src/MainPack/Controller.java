package MainPack;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import java.util.Random;
import java.lang.*;

public class Controller
{
    @FXML
    private Text sign;

    @FXML
    private Button start_but;

    @FXML
    private Text score_text;

    @FXML
    private ImageView red_car;

    @FXML
    private ImageView blue_car;

    @FXML
    private Button pause_but;

    private boolean is_game = false;
    private boolean is_pause = false;
    private boolean is_winner = false;
    private String str;

    Thread thr1;
    Thread thr2;

    public void pause_game()
    {
        is_pause = !is_pause;
        if(is_pause)
        {
            pause_but.setText("Resume");
            thr1.suspend();
            thr2.suspend();
        }
        else
        {
            thr1.resume();
            thr2.resume();
            pause_but.setText("Pause");
        }
    }

    public void start_game(ActionEvent act)
    {
        if(!is_game)
        {
            sign.setVisible(false);
            score_text.setVisible(false);
            red_car.setX(0);
            blue_car.setX(0);

            is_game = true;
            is_winner = false;
            if(new Random().nextInt()%2 == 0)
            {
                thr1 = new CarPlayer("Blue car", blue_car);
                thr2 = new CarPlayer("Red car", red_car);
                thr1.start();
                thr2.start();
            }
            else
            {
                thr1 = new CarPlayer("Blue car", blue_car);
                thr2 = new CarPlayer("Red car", red_car);
                thr2.start();
                thr1.start();
            }
        }
    }

    int Carcoord(ImageView car, int step)
    {
        if(!is_game) return 0;
        double div_step = (double)step / 8;
        car.setX(car.getX() + div_step);
        return (int) car.getX();
    }

    public void end_game(String str)
    {
        if(str.equals("blue_car") && !is_winner)
        {
            is_winner = true;
            Main.blue_count++;
            sign.setText("Blue wins!");
        }
        else if(str.equals("red_car") && !is_winner)
        {
            is_winner = true;
            Main.red_count++;
            sign.setText("Red wins!");
        }
        score_text.setText("Score | R:" + Main.red_count + " B:" + Main.blue_count);
        sign.setVisible(true);
        score_text.setVisible(true);
    }

    class CarPlayer extends Thread
    {
        private ImageView car;
        CarPlayer(String name, ImageView car)
        {
            super(name);
            this.car = car;
        }

        @Override
        public void run()
        {
            Random rand = new Random();
            while(is_game)
            {
                int step = rand.nextInt(60) + 40;
                if (Carcoord(car, step) >= 625)
                {
                    is_game = false;
                    end_game(car.getId());
                }
                else
                {
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

