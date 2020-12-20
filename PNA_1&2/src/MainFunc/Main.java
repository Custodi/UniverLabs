package MainFunc;
import java.util.Random;
import Tribe.Tribe;

public class Main {

    public static void main(String[] args)
    {
        Random rand = new Random();

        Tribe[] t_arr = new Tribe[10];
        for (int i = 0; i < t_arr.length; i++)
        {
            t_arr[i] = new Tribe("Tribe #" + (i + 1), rand.nextInt(120), rand.nextBoolean());
            System.out.print("----------\n");
            t_arr[i].get_info();
            System.out.print("----------\n");
        }

        System.out.println("Summary count of people: " + Tribe.get_summary_count(t_arr));
        System.out.println("How many tribes familiar with fire? " + Tribe.get_summary_is_fired(t_arr));
    }
}
