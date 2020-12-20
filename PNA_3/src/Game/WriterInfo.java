package Game;

public class WriterInfo
{
    public WriterInfo(Stats obj)
    {
        System.out.println("Name: " + obj.getName() + "\nHealth: " + obj.getHealth() + "\nStrength: " + obj.getStrength() +
                "\nAgility: " + obj.getAgility() + '\n');
        if(obj instanceof Mage)
        {
            Mage m = (Mage)obj;
            System.out.println("Type of magic: " + m.getMagic());
        }
        else if(obj instanceof Monster)
        {
            Monster mons = (Monster)obj;
            System.out.println("Length of teeth and claws: " + mons.getLength_of_teeth() + " and " + mons.getLength_of_claws() +
                    "\nCount of paws: " + mons.getCount_of_paws());
        }
        else if(obj instanceof Warrior)
        {
            Warrior w = (Warrior)obj;
            System.out.println("Type of sword: " + w.getWeapon() + "\nType of shield: " + w.getShield());
        }
    }
}
