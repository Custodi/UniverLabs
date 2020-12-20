package MainFunc;
import Game.*;

public class Main
{
    public static void main(String[] args)
    {
        Mage _m = new Mage();
        Warrior _w = new Warrior("Mark", 100, "Sword");
        Monster_Fast _s = new Monster_Fast();
        Monster_Boss _b = new Monster_Boss("Devil", 2000, 300, 100, 2.4, 13.9, 23,
                240.3);

        WriterInfo ww = new WriterInfo(_m);
        WriterInfo ww1 = new WriterInfo(_w);

        _s.print();
        _b.print();

        _s.attack();
        _b.attack();
    }
}
